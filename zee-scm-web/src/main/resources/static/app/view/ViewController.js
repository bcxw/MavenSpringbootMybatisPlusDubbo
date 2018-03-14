Ext.define('app.view.ViewController', {
	extend: 'Ext.app.ViewController',
	alias : 'controller.viewController',
    logout:function(){
		Ext.MessageBox.confirm('系统提示', '您确定要注销登录吗？',function(btn){
			if(btn=="yes"){
                Ext.Ajax.request({
                    url: 'user/logout',
                    success: function(response, opts) {
                        var obj = Ext.decode(response.responseText);
                        if(obj.success){
                            Ext.Msg.alert('系统提示',"注销退出系统成功！");
                        }else{
                            Ext.Msg.alert('操作失败',obj.message);
                        }
                    },
                    failure: function(response, opts) {
                        Ext.Msg.alert('操作失败',response.status+response.responseText);
                    }
                });
				location.href="login.html";
			}

		});
	}
	
});