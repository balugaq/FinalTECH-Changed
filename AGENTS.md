# AGENTS.md — FinalTECH-Changed 操作宪章

## TL;DR

1. **禁止启动浏览器或任何渲染/截图工具做验证**，静态核对用 `./gradlew build` + grep。
2. Java 版本固定 **21**（`compileJava.options.release = 21`），不要动。
3. 依赖版本以 `gradle/libs.versions.toml` 为准（原 Maven 坐标已记录在注释里）。
4. 不 commit / 不 push，提交权归主人；只把工作区留到可通过验收的状态。
5. 文案改动一律进语言文件（`src/main/resources/language/*.yml`），不要硬编码在 Java 里。

## 1. 项目身份与边界

- **项目**：FinalTECH-Changed（乱序技艺改版），Slimefun4 附属 Bukkit 插件。
- **上游**：原作者 Final_ROOT 的 FinalTECH 原仓库已删除/私有，无可 pull 的上游，不存在 pull.yml。
- **owner**：`balugaq`，仓库 `balugaq/FinalTECH-Changed`，默认分支 `main`。
- **定位**：适配简中粘液 + 平衡性调整 + bug 修复 + 汉化完善。

### 1.1 代码包地图

```
io.taraxacum.common                  通用工具（无 Slimefun 依赖）：MathUtil、StringNumberUtil、ReflectionUtil 等
io.taraxacum.finaltech               主类 FinalTechChanged（onEnable 里做全部启动装配）
io.taraxacum.finaltech.core.command  4 条命令实现
io.taraxacum.finaltech.core.item     AbstractMySlimefunItem 及其下的 machine/、unusable/ 等物品实现
io.taraxacum.finaltech.core.listener 监听器（BoxListener、ShineListener 等 4 个）
io.taraxacum.finaltech.core.patch    对 Slimefun 原有类的覆写（EnergyRegulatorBlockTicker 等）
io.taraxacum.finaltech.core.networks 货运网络核心
io.taraxacum.finaltech.setup         注册入口：FinalTechItemStacks（241 个物品常量）、FinalTechItems、FinalTechMenus、FinalTechRecipeTypes、FinalTechRecipes、SetupUtil、Updater
io.taraxacum.finaltech.util          工具类与常量表
```

## 2. 技术栈与项目结构

- Java 21（`compileJava.options.release = 21`），Gradle（Kotlin DSL）+ version catalog + shadow（relocate `org.bstats` → `io.taraxacum.finaltech`，不开 minimize）
- 依赖：paper-api 1.21.11（compileOnly）、Slimefun4 2025.1 简中版（jitpack com.github.SlimefunGuguProject，compileOnly）、bstats-bukkit 3.0.0（implementation，会被 shade）
- 资源：`plugin.yml`（version 占位符是 `${version}`，由 `processResources` 展开）、`config.yml` / `item.yml` / `value.yml` / `template.yml`、`language/zh-CN.yml`（默认）、`zh-TW.yml`、`en-US.yml`
- `crowdin.yml`：翻译平台配置，保留原样

## 3. 决策树

### 3.1 新增/修改 Slimefun 物品或机器

1. 在 `FinalTechItemStacks` 加 SlimefunItemStack 常量
2. 在 `FinalTechItems` 注册物品与配方
3. 文案进 `language/zh-CN.yml`（zh-TW / en-US 尽量同步）
4. 需要特殊菜单结构时改 `FinalTechMenus` / `FinalTechRecipeTypes`

### 3.2 配置与平衡性调整

平衡参数尽量走 `config.yml` 的 `tweak.*`（range-limit / anti-accelerate / performance-limit / interval / no-blockTicker），不要写死在代码里。

### 3.3 异常与日志

用 `FinalTechChanged.logger()`（CustomLogger），不要直接 `System.out`。

### 3.4 构建与验收

`./gradlew clean build`，产物 `build/libs/FinalTECH-Changed-<版本>.jar`；改 YAML 后至少做语法解析检查。

## 4. 红线

1. 禁止启动浏览器/渲染/截图工具做视觉验证。
2. 禁止改 git 历史、禁止 commit/push。
3. 禁止把本机 JDK 绝对路径写进 `gradle.properties`。
4. 禁止降级 Java 版本或换回官方仓库 Slimefun4（当前绑定简中版 2025.1，含数据库存储）。
5. 跨版本常量优先用粘液兼容类（`VersionedParticle` 等），不要直接写 `org.bukkit.Particle`/`Material` 的新常量。
6. 不碰 `target/`（旧 Maven 构建产物，仅存档用）。

## 5. CI

- `build.yml` / `release.yml`：JDK 21，`chmod +x gradlew` 已内置
- `test-compatibility.yml`：矩阵 `1.20.1,26.2`，从鬼斩资源站下载 Slimefun4 作前置
- `gradlew` 的 git mode 必须是 `100755`（`git ls-files -s gradlew` 检查）

## 6. 已知债务

- 启动时有一条物品注册报错（上游遗留，可忽略）
- `FinalTechChanged` 若干 TODO（config GUI、reload 补全）
- `template.yml` 模板机器注册被注释（代码标注"需要更多测试"）
