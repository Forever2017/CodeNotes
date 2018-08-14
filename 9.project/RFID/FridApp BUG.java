ok 1、 反库 -》 返库    
ok 5、核对 -> 核对拣货单  table名字  
ok 7、核对    核实->确认 Button
ok 8、核对    扫描-》扫描商品
ok 10、核对     核实成功 -》提交成功   MSG
ok 11、保险箱   待移库 -》 待接收    文字
ok 12、保险箱   待移库单 -  待接收单


ok 3、手动同步-》  只显示 “同步成功” 的弹框 \ 同步失败



code - 2、盘点-》扫描 -》 全部找到，自动停止
code - 9、核对    列表-》增加 清除 按钮（清除item当前已找到状态，改成未找到）
code - 6、核对    提交核对->提交（之前显示灰色按钮，list全部核对后再显示）
//测试提交按钮

code -4、Service返回的结果为  （Un os    Http 401 - 》）-》 直接跳到登陆页（别的什么都不做）

	org.apache.http.client.HttpResponseException: Unauthorized
	401


ok 14、
{
	　　　　　　"productCode":"P0001",
	　　　　　　"subProductCode":"SP001",
	　　　　　　"productExternalId":"3608d039794d4a1bb4df2d15e3bd01d2",
	　　　　　　"productName":"NO1 Product",
	　　　　　　"price":10,
	　　　　　　"epc":"E0000001",
	　　　　　　"rfidCode":"a30de80143224324b49d142ca19c22c0",
	　　　　　　"status":2
} 数据展示的时候，有长码的，都显示  　"epc":"E0000001",
	
	

ok 移库 -》 接收
ok 确认移库=》确认接收
ok 待移库商品=》待接收商品
ok 移库成功=》接收成功


