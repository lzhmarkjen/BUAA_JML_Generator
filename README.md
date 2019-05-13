手撸的一个数据生成器

面向OO的第二次第三次JML（第三单元）作业，请切换对应分支

在Main内修改生成的指令条数``querySize``，在Generator内修改最大路径长度``maxPathLength``、最大最小节点编号``maxNode,minNode``，还有”图修改指令“的条数``Super``。

以下摘自第11次作业指导书

------------------

### 指令格式一览

#### 添加路径

输入指令格式：`PATH_ADD 结点序列`

举例：`PATH_ADD 1 2 3`

输出：

- `Ok, path id is x.` x是调用返回的路径id，如果容器中已有相同的路径，则返回已有的路径id，否则返回添加后的路径id

#### 删除路径

输入指令格式：`PATH_REMOVE 结点序列`

举例：`PATH_REMOVE 1 2 3`

输出：

- `Failed, path not exist.`  容器中不存在结点序列对应的路径
- `Ok, path id is x.` 容器中存在路径，x是被删除的路径id

#### 根据路径id删除路径

输入指令格式：`PATH_REMOVE_BY_ID 路径id`

举例：`PATH_REMOVE_BY_ID 3`

输出：

- `Failed, path id not exist.`  容器中不存在路径id对应的路径
- `Ok, path removed.` 容器中存在路径，x是被删除的路径id

#### 查询路径id

输入指令格式：`PATH_GET_ID 结点序列`

举例：`PATH_GET_ID 1 2 3`

输出：

- `Failed, path not exist.`  容器中不存在结点序列对应的路径
- `Ok, path id is x.` 容器中存在路径，x是被查询的路径id

#### 根据路径id获得路径

输入指令格式：`PATH_GET_BY_ID 路径id`

举例：`PATH_GET_BY_ID`

输出：

- `Failed, path id not exist.` 容器中不存在路径id对应的路径
- `Ok, path is x.` 容器中存在路径，x是用英文圆括号包起来的结点序列，例如：`Ok, path is (1 2 3).`

#### 获得容器内总路径数

输入指令格式：`PATH_COUNT`

举例：`PATH_COUNT`

输出：`Total count is x.` x是容器内总路径数

#### 根据路径id获得其大小

输入指令格式：`PATH_SIZE 路径id`

举例：`PATH_SIZE 3`

输出：

- `Failed, path id not exist.`  容器中不存在路径id对应的路径
- `Ok, path size is x.` 容器中存在路径，x是该路径的大小

#### 根据路径id获取其不同的结点个数

输入指令格式：`PATH_DISTINCT_NODE_COUNT 路径id`

举例：`PATH_DISTINCT_NODE_COUNT 5`

输出：

- `Failed, path id not exist.`  容器中不存在路径id对应的路径
- `Ok, distinct node count of path is x.` 容器中存在路径，x是路径中不同的结点个数

#### 根据结点序列判断容器是否包含路径

输入指令格式：`CONTAINS_PATH 结点序列`

举例：`CONTAINS_PATH 1 2 3`

输出：

- `Yes.` 如果容器中包含该路径
- `No.` 如果容器中不包含该路径

#### 根据路径id判断容器是否包含路径

输入指令格式：`CONTAINS_PATH_ID 路径id`

举例：`CONTAINS_PATH_ID 3`

输出：

- `Yes.` 如果容器中包含该路径
- `No.` 如果容器中不包含该路径

#### 容器内不同结点个数

输入指令格式：`DISTINCT_NODE_COUNT`

举例：`DISTINCT_NODE_COUNT`

输出：`Ok, distinct node count is x.` x是容器内不同结点的个数

#### 根据字典序比较两个路径的大小关系

输入指令格式：`COMPARE_PATHS 路径id 路径id`

举例：`COMPARE_PATHS 3 5`

输出：

- `Ok, path x is less than y.` 字典序x小于y，x是输入的第一个路径id，y是第二个路径id
- `Ok, path x is greater than y.` 字典序x大于y，x是输入的第一个路径id，y是第二个路径id
- `Ok, path x is equal to y.` 字典序x等于y，x是输入的第一个路径id，y是第二个路径id
- `Failed, path id not exist. ` 输入的两个路径id中任意一个不于容器中存在

#### 路径中是否包含某个结点

输入指令格式：`PATH_CONTAINS_NODE 路径id 结点`

举例：`PATH_CONTAINS_NODE 3 5`

输出：

- `Failed, path id not exist.`  容器中不存在路径id对应的路径
- `Yes.` 容器中存在该路径，且包含此结点
- `No.` 容器中存在该路径，但不包含此结点

#### 容器中是否存在某个结点

输入指令格式：`CONTAINS_NODE 结点id`

举例：`CONTAINS_NODE 3`

输出：

- `Yes.` 容器中存在结点id对应的结点
- `No.` 容器中不存在结点id对应的结点

#### 容器中是否存在某一条边

输入指令格式：`CONTAINS_EDGE 起点结点id 终点结点id`

举例：`CONTAINS_EDGE 3 5`

输出：

- `Yes. ` 容器中存在以起点结点id和终点结点id为两个端点的一条边
- `No. ` 容器中不存在以起点结点id和终点结点id为两个端点的一条边

#### 两个结点是否连通

输入指令格式：`IS_NODE_CONNECTED 起点结点id 终点结点id`

举例：`IS_NODE_CONNECTED 3 5`

输出：

- `Yes. ` 容器中的各路径构成的图上，起点节点和终点节点之间连通（即在同一连通块上）
- `No. ` 容器中的各路径构成的图上，起点节点和终点节点之间不连通（即不在同一连通块上）
- `Failed, node id not exist.` 输入的两个结点id中任意一个不于容器中存在

#### 两个结点之间的最短路径

输入指令格式：`SHORTEST_PATH_LENGTH 起点结点id 终点结点id`

举例：`SHORTEST_PATH_LENGTH 3 5`

输出：

- `Ok, length is len.` len为最短路径的长度
- `Failed, node id not exist.` 输入的两个结点id中任意一个不于容器中存在
- `Failed, node not connected with each other.` 输入的两个结点id不相连

#### 整个图中的连通块数量

输入指令格式：`CONNECTED_BLOCK_COUNT`

举例：`CONNECTED_BLOCK_COUNT`

输出：

- `Ok, connected block count is x.` x是整个图中的连通块数量(连通块的定义请参考图论)

#### 两个结点之间的最低票价

输入指令格式：`LEAST_TICKET_PRICE 起点结点id 终点结点id`

举例：`LEAST_TICKET_PRICE 5 9`

输出

- `Ok, least price is x.`  x是两个结点之间的最低票价
- `Failed, node id not exist.`  输入的两个结点id中任意一个不于容器中存在
- `Failed, node not connected with each other.` 输入的两个结点id不相连

#### 两个结点之间的最少换乘次数

输入指令格式：`LEAST_TRANSFER_COUNT 起点结点id 终点结点id`

举例：`LEAST_TRANSFER_COUNT 5 9`

输出

- `Ok, least transfer count is x.`  x是两个结点之间的最少换乘次数
- `Failed, node id not exist.`  输入的两个结点id中任意一个不于容器中存在
- `Failed, node not connected with each other.` 输入的两个结点id不相连

#### 两个结点之间的最少不满意度

输入指令格式：`LEAST_UNPLEASANT_VALUE 起点结点id 终点结点id`

举例：`LEAST_UNPLEASANT_VALUE 5 9`

输出

- `Ok, least unpleasant value is x.`  x是两个结点之间的最少不满意度
- `Failed, node id not exist.`  输入的两个结点id中任意一个不于容器中存在
- `Failed, node not connected with each other.` 输入的两个结点id不相连