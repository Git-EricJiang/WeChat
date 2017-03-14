var appid = 'wxa28ef2be12d087d4';  
var appsecret = 'ba9ce30c6d0780ec0a7179035fb02480';  
var url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={"+appid+"}&secret="+appsecret;  

var token;
$.ajax({
    url : url,//TODO
    type : "get",
    success : function(data) {
    	console.dir(data.access_token);
    	token = data.access_token;
    }
});


var d =  {
	     "button":[
	               {	
	                    "type":"click",
	                    "name":"今日歌曲",
	                    "key":"V1001_TODAY_MUSIC"
	                },
	                {
	                     "name":"菜单",
	                     "sub_button":[
	                     {	
	                         "type":"view",
	                         "name":"搜索",
	                         "url":"http://www.soso.com/"
	                      },
	                      {
	                         "type":"view",
	                         "name":"视频",
	                         "url":"http://v.qq.com/"
	                      },
	                      {
	                         "type":"click",
	                         "name":"赞一下我们",
	                         "key":"V1001_GOOD"
	                      }]
	                 }]
	           }
$.ajax({
    url : "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+token,//TODO
    type : "post",
    data : d,
    success : function(data) {
    	console.dir(data.access_token);
    }
});