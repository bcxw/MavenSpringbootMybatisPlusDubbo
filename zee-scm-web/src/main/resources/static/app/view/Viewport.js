Ext.define('app.view.Viewport', {
    extend: 'Ext.container.Viewport',
	
	requires:["app.view.ViewModel","app.view.ViewController","app.main.MainPanel"],
	//viewModel:{type:"viewModel",url:"configAction/getConfig.action"},
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
		}/*,{
			xtype:'tbspacer',
			flex:1
		},{
			xtype:'toolbar',
			defaults:{
				scale: 'medium',
				height:31,
				style:{"background":"#FFF"}
			},
			items:[{
				tooltip:'当前用户',
				glyph:0xf007,
				bind: {text:"{userName}"},
			},{
				tooltip:'注销登录',
				glyph:0xf011,
				handler:"logoff"
			}]
		}*/]
    },{
        region: 'center',
		xtype:"tabpanel",
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
		store:{
			//autoLoad:true,
			type:"tree",
			//sorters:'sort',
			//defaultRootProperty:"data",//按照data属性读取节点，默认是children
			fields: [{
				name:'id'
			},{
				name:'parentId'
			},{
				name:'parentName'
			},{
				name:'text'
			},{
				name:'uri'
			},{
				name:'icon'
			},{
				name:'sort'
			}]/*,
			proxy: {
				type:'ajax',
				url:'menuAction/getMenuTree.action'
			}*/
			,data:[{
				text:'商品企划',
                expanded: true,
				children:[{
					text:'成衣企划',
                    expanded: true,
					children:[{
						text:'企划计划',
						glyph:0xf013,
						uri:'app.productLayout.layoutPlan.LayoutPlanGridPanel',
						leaf:true
					}]
				}]
			}]
		},
		//bind:{store:'{menuTreeStore}'},//以后要修改程自己的store，要有权限的
		rootVisible:false,
		root: {
			id:"root",
			text:'顶级菜单',
			name:'顶级菜单',
			expanded: true
		},
		listeners:{
			/*itemexpand:function( node, eOpts ){
				if(node.id=="root"&&node.firstChild)node.firstChild.expand();
			},*/
			itemclick:function( cmp, record, node, index, e, eOpts ){
				var uri=record.data.uri;
				if(uri&&uri.indexOf("app.")==0){
					var tabPanel=cmp.up("viewport").down("tabpanel");
					var panel=tabPanel.down("[uri='"+uri+"']");
					if(!panel){
						panel=Ext.create(uri,{title:record.data.text,icon:record.data.icon,glyph:record.data.glyph,uri:uri});
						tabPanel.add(panel);
					}
					tabPanel.setActiveTab(panel);
				}

			}
		}
    }]
});