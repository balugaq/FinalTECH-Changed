# FinalTECH-Changed

乱序技艺改版：新增两百多种机器与物品，含货运仓储与熵体系，内置简中适配、防加速与防卡顿调节。

对乱序技艺（FinalTECH，原作者 Final_ROOT，原仓库已删除/私有）进行的维护改版：适配最新版简中粘液（Slimefun4 简中版），并进行大量平衡性调整、配方调整、机制合理化与 bug 修复，完善并补全了简中文案。

## 功能概览

- **新增 200+ 物品与机器**：货运传输（点/线/面/网及高级变体）、仓储端口、扩展电容、逻辑比较器、手动合成机系列、熵体系（熵种/等概念体/合理性）等，含专属分类与研究
- **4 条命令**：
  - `/finaltech-copy-card` — 把有效物品转为复制卡
  - `/finaltech-storage-card <数量>` — 把有效物品转为指定数量的存储卡
  - `/finaltech-info` — 查看粘液物品信息
  - `/finaltech-valid-item` — 使物品有效化
- **多语言**：简体中文（默认）/ 繁体中文 / English
- **配置化调节**（`config.yml`）：机器范围限制、防加速、性能限制、跳过 BlockTicker、强制异步、多线程级别、数据丢失修复等，均可在配置中开关或逐物品调整

## 前置

- 服务端建议 1.20.4+（Slimefun4 2025.1 要求）
- [Slimefun4 2025.1+（简中版）](https://github.com/SlimefunGuguProject/Slimefun4)（硬依赖）

## 安装

1. 装好 Slimefun4
2. 把 `FinalTECH-Changed v<版本>.jar` 放进 `plugins/`
3. 重启服务器，首次启动会生成 `config.yml` / `item.yml` / `value.yml` / `template.yml` 等配置

与其他版本的乱序技艺可共存，但物品不互通兼容。

## 注意事项

- 加载时会出现一条物品注册相关报错，可忽略，不影响任何功能
- 部分机器描述为默认参数下的数据，实际以配置文件为准
- 功能较强的物品（如货运、熵种）可能带来粘液刻延迟，酌情禁用
- 熵种（`_FINALTECH_ENTROPY_SEED`）请严格管控：短时间内放置过多会造成较大延迟，可用「熵清除器」右键开启清除模式

## 构建

```bash
./gradlew clean build
```

产物在 `build/libs/FinalTECH-Changed-<版本>.jar`（Java 21）。

## 依赖升级适配记录（2026-11-07）

- spigot-api 1.19.3 → paper-api 1.21.11，编译版本 Java 17 → 21
- Slimefun4 RC-33（官方仓库）→ Slimefun4 2025.1（简中版 GuguProject）
- `Particle.CRIT_MAGIC` → `VersionedParticle.ENCHANTED_HIT`（粘液跨版本兼容类）
- `Material.CHAIN` → `Material.IRON_CHAIN`（1.21.11 引入铜链系列后更名）
- `BlockStorage.hasInventory(Location)` → `hasInventory(Block)`
- `EnergyNet.tick(Block)` → `tick(Block, SlimefunBlockData)`（通过 `StorageCacheUtils.getBlock` 获取）
- `BlockStorage.saveChunks()` / `storage.save()` 已移除且无替代 API：2025.1 改用数据库存储自动保存，onDisable 中相关调用删除

## 反馈

通过 [Issues](https://github.com/balugaq/FinalTECH-Changed/issues) 反馈问题。

## 许可

见 [LICENSE](LICENSE)。
