# FinalTECH-Changed
对于乱序技艺(FinalTECH)进行最新版的简中粘液适配,同时进行大量平衡性魔改,bug修复

原作者Final_ROOT关于乱序技艺库已删除/私有(github上只有克隆版本)

基于Final_ROOT的乱序技艺(FinalTECH)构建75版本, 进行大量的参数调整, 配方平衡性调整, 机制合理化, 修复了部分bug, 完善了汉化以及纠正补全了汉化描述

同时对简中粘液进行适配

同时允许与其他版本的乱序共存, 但是物品并不互通兼容

配置文件提供了大量可调整参数, 部分机器描述仅供参考, 为默认参数情况下的数据

加载时会发生一个关于物品注册的错误, 请忽略它, 它并不影响任何东西!

自行构建

# 一定要看的注意事项!!!!!

1.较于构建29的乱序确实优化了许多，但是毕竟功能强大的同时（比如货运）肯定有一定后果，所以还是会有一定的延迟!

1.1.关于链式卡tps的问题已经修了，但是粘液刻延时可能偏高，大概在0.1~0.5ms间

2.有些物品功能过于强大，会带来一些粘液刻延迟，酌情ban物！

3.关于熵种(_FINALTECH_ENTROPY_SEED)你必须要知道的！

  3.1简介 这是一个乱序的终极物品用于产出 等概念体 与 合理性 这个两个终极材料, 自身玩家需要很长时间(排除其他附属的辅助效益可能也需要几个月)才可能获得一个(除非玩家获得了复制物品性质的东西)

  3.2效果 放下后自身转变为一个带数据的 等概念体
  
    3.2.1 生产出的等概念体会继续往周围随机生成 等概念体 与 合理性, 所有等概念体生成几秒后会变为 合理性

    3.2.2 关于合理性, 产出后5刻自动消散

    3.2.3 上述过程约会产出几万合理性与几万等概念体, 会在一小段时间内产生较大的粘液刻延迟

 3.3如何停止他的扩散 
 
   通过等待, 一段时间后会全部消散;
   
   我们在物品里面也提供了 熵清除器 , 右键即可打开清除模式, 开始删除所有的等概念体与合理性, 再次右键即可关闭清除模式;

 3.4可能的后果 
   
   如果只摆放了一个用于获取终极材料, 你们并不需担心, 因为几分钟后100%可以恢复; 
   
   如果短时间内放了过多的该物品, 粘液刻会在一段时间内有较大延迟, 此时请迅速使用熵清除器!!!

 3.5建议处理
 
   ·严格管控, 平时禁用, 每次有玩家要用时让管理员进行解禁, 玩家用 1伪物+24x64熵兑换一次使用权, 继而管理摆放一个让玩家自行挖掘产出物品; 
   
   ·提供兑换, 用 1伪物 + 24x64熵 兑换 等概念体与合理性(推荐比例 1伪物+24x64熵 => 192等概念体 + 1024合理性);
   
   ·制定规章, 通过惩戒来阻止用此卡粘液刻, 另外积极备份;
   
   ·放任不管, 可能被刷物者用于破坏服务器, 但是正常玩家并不会因此产生太大影响;
   
   ·直接完全禁用, 我不推荐如此, 因为会破坏乱序的完整性, 毕竟这是一个获取终极材料的必要步骤!
   

最后祝您游玩愉快


