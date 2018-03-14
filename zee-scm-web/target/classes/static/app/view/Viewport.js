Ext.define('app.view.Viewport', {
    extend: 'Ext.container.Viewport',
	
	requires:["app.view.ViewModel","app.view.ViewController","app.main.MainPanel"],

	viewModel:{type:"viewModel",url:"user/getConfig"},
	controller:'viewController',
	
	layout: 'border',
    items: [{
        region: 'north',
        height:60,
		layout:"hbox",
		border:false,
		bodyStyle:{"background":"url(images/main/banner.png) #037ec2 no-repeat"},
		defaults:{
			border:false,
			height:60,
			bodyStyle:{"background":"none"},
			style:{"background":"none"}
		},
		items: [{
			minWidth:500,
			items:[{
				xtype:"image",
				src:'images/main/logo.png',
				height:60,
				weight:60,
				margin:2,
				style:"display:block;float:left"
			},{
				xtype:"label",
                text:"供应链管理系统",
				style:"margin-left: 75px;font-size:28px;line-height: 70px;display:block;float:left;height:50px;margin:0px;font-weight:bold;color:#fff;"
			}]
		},{
			xtype:'tbspacer',
			flex:1
		},{
			xtype:'toolbar',
			defaults:{
				scale: 'small',
				height:25,
				style:{"background":"#FFF"}
			},
			items:[{
                tooltip:'消息中心',
                glyph:0xf0f3,
			},{
				tooltip:'当前用户',
				glyph:0xf007,
				bind: {text:"{username}"},
                menu: [{
                    text:'退出登录',
                    handler:"logout"
                }]
			}]
		}]
    },{
        region: 'center',
		xtype:"tabpanel",
		id:"appTabPanel",
		items:[{xtype:"mainPanel"}]
    },{
        region: 'west',
		xtype:"treepanel",
		title:'菜单导航',
		glyph:0xf0ca,
        width:200,
		collapsible:true,
        split:true,
		useArrows:true,

        rootVisible:false,
        store:{
            autoLoad:true,
            type:"tree",
            sorters:'sortorder',
            defaultRootProperty:"data",
            root: {
                id:"root",
                text:'顶级菜单',
                name:'顶级菜单',
                expanded: true
            },
            fields: [{
                name:'id'
            },{
                name:'text'
            },{
                name:'uri'
            },{
                name:'icon'
            },{
                name:'sortorder'
            }],
            proxy: {
                type:'ajax',
                url:'user/getMenu'
            },
            listeners:{
                load:function( store, records, successful, operation, node, eOpts ){
                    if(records&&records.length>0){
                        records[0].expand();
                    }
                }
            }

        },
		listeners:{
			itemclick:function( cmp, record, node, index, e, eOpts ){
				var uri=record.data.uri;
				if(uri&&uri.indexOf("app.")==0){
					var tabPanel=cmp.up("viewport").down("tabpanel");
					var panel=tabPanel.down("[uri='"+uri+"']");
					if(!panel){
						panel=Ext.create(uri,{title:record.data.text,icon:record.data.icon,glyph:record.data.glyph,uri:uri});
						tabPanel.add(panel);
						if(record.data.authData&&Ext.isObject(record.data.authData)){
                            Ext.Object.each(record.data.authData, function(key,value,myself) {
                                panel.getViewModel().set(key,value);
                            });

						}
                    }
					tabPanel.setActiveTab(panel);
				}

			}
		}
    }]
});