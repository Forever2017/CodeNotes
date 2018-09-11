###########################################################################################################

//最大列表
http://api.yy2025.com/api/zhibo/flatbed/getList
{
                "id":"63b3ffc1ec5c4e97b9ae83a6d211105b",
                "name":"小清新",
                "logo":"http://www.yy2025.com/userfiles/1/files/zhibo/flatbed/2018/07/xiaoqingxin_logo.jpg",
                "quantity":210,
                "channel":null,
                "msgType":"1",
                "livePlugin":"63b3ffc1ec5c4e97b9ae83a6d211105b"
            },

//子列表
http://api.yy2025.com/api/zhibo/flatbedAnchor/list

app=nice&livePluginId=63b3ffc1ec5c4e97b9ae83a6d211105b&token=27376a7e6be04494b45ea73063621964&
userId=69dfcfce49f64159bc25e77be3e1b9a3

{
            "roomId":"546710",
            "no":"1514119",
            "title":"",
            "nicename":"萌萌的提子",
            "playUrl":"",
            "quantity":23586,
            "imageThumb":"http://o.qingsonghr.com/public/attachment/201808/02/11/origin/15331507311514119.jpg?x-oss-process=image/resize,m_mfit,h_150,w_150",
            "image":"http://o.qingsonghr.com/public/attachment/201808/02/11/origin/15331507311514119.jpg?x-oss-process=image/resize,m_mfit,h_260,w_260",
            "groupId":"",
            "appId":"",
            "livePlatformId":"63b3ffc1ec5c4e97b9ae83a6d211105b"
        },

//房间详情
http://api.yy2025.com/api/zhibo/flatbedAnchor/info
app=nice&livePluginId=63b3ffc1ec5c4e97b9ae83a6d211105b&
no=1514119&userId=69dfcfce49f64159bc25e77be3e1b9a3

"result":{
        "no":"1514119",
        "image":"http://o.qingsonghr.com/public/attachment/201808/02/11/origin/15331507311514119.jpg?x-oss-process=image/resize,m_mfit,h_260,w_260",
        "quantity":23112,
        "is_follow":false,
        "groupId":"@TGS#aOCEXJLF3",
        "appId":"1400115259",
        "livePlatformId":"63b3ffc1ec5c4e97b9ae83a6d211105b",
        "nicename":"萌萌的提子",
        "imageThumb":"http://o.qingsonghr.com/public/attachment/201808/02/11/origin/15331507311514119.jpg?x-oss-process=image/resize,m_mfit,h_150,w_150",
        "title":"",
        "roomId":"546710",
        "playUrl":"http://a18.shahao44.top/live/546710_1091e1b1ce11d145d7f3.flv?auth_key=1533648632-0-0-ee1a794b693e2d830196b4196bf48d9e"
    }
	  
	  
	  
	  /* 获取个人info,感觉没啥用
     http://api.yy2025.com/api/zhibo/member/info?app=nice&token=4f3d5cc863444739a6117e41bea64e52&userId=dfdcbc7f97074ca19b0a7c9dbb648ad6
     */
    /* 获取所有直播平台列表
     http://api.yy2025.com/api/zhibo/flatbed/getList
     */
    /*获取当前直播平台列表
     http://api.yy2025.com/api/zhibo/flatbedAnchor/list?app=nice&livePluginId=63b3ffc1ec5c4e97b9ae83a6d211105b&token=0f8420f60e4a43ca9b4d60e59919f5a9&userId=161be15eeeea48f384ae3bba624f60d0
     */
    /*获取当前直播info
     http://api.yy2025.com/api/zhibo/flatbedAnchor/info?
	 app=nice
	 &livePluginId=63b3ffc1ec5c4e97b9ae83a6d211105b
	 &no=241213
	 &userId=dfdcbc7f97074ca19b0a7c9dbb648ad6
     */
	  
	  
	  
	  //获取验证码
	  http://api.yy2025.com/api/zhibo/member/getValidateCode
	  
	  //注册
	  http://api.yy2025.com/api/zhibo/member/register?
	  app=nice&countryCode=86&invitationCode=AAA111&
	  
	  password=oqhwg60326965&
	  token=522fa7285860455aa687be5a74b9001f&username=14747939305&validateCode=4ya1
	  
	  
	  
	  //登陆
	  
	  http://api.yy2025.com/api/zhibo/member/login?app=nice&countryCode=86&password=3raixo&username=15205171203
	  
	  
	  
	  
	  
	  
//房间详情	  
http://api.yy2025.com/api/zhibo/flatbedAnchor/info?app=nice&livePluginId=63b3ffc1ec5c4e97b9ae83a6d211105b&no=62093&userId=de0aa3bbbf834eaf86d3d7f52acddb97
	
	 app=nice //写死
	 &livePluginId=63b3ffc1ec5c4e97b9ae83a6d211105b //无所谓
	 &no=241213  //房间号码(永远不会变，如果查询不出来，就是list没有返回 关闭？或者未直播？)
	 &userId=dfdcbc7f97074ca19b0a7c9dbb648ad6 // 需要新的	"用户会员已过期",
	 
	 
{
"code": "02",
"type": false,
"message": "用户会员已过期",
"result": null
}  
	
{  正常的数据
"code": "00",
"type": true,
"message": "操作成功",
"result": {
	"no": "1283424",
	"image": "http://o.qingsonghr.com/public/attachment/201807/20/04/5b50f8d5d0b2d.png?x-oss-process=image/resize,m_mfit,h_260,w_260",
	"quantity": 409,
	"is_follow": false,
	"groupId": "@TGS#aJEMYOLFH",
	"appId": "1400115259",
	"livePlatformId": "63b3ffc1ec5c4e97b9ae83a6d211105b",
	"nicename": "YH蒙蒙",
	"imageThumb": "http://o.qingsonghr.com/public/attachment/201807/20/04/5b50f8d5d0b2d.png?x-oss-process=image/resize,m_mfit,h_150,w_150",
	"title": "",
	"roomId": "582670",
	"playUrl": "http://a10.shahao44.top/live/582670_1be1a68e1776e9bce095.flv?auth_key=1533909462-0-0-a63f36414ab0c243739defcd5c7bfa99"
	}
}
	
{  房间号码返回
	"code": "00",
	"type": true,
	"message": "操作成功",
	"result": {
	"no": "",
	"image": "",
	"quantity": 0,
	"is_follow": false,
	"groupId": "",
	"appId": "",
	"livePlatformId": "",
	"nicename": "",
	"imageThumb": "",
	"title": "",
	"roomId": "",
	"playUrl": ""
}
  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  