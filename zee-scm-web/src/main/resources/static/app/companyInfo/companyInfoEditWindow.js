Ext.define('app.companyInfo.companyInfoEditWindow',{
    extend: 'Ext.window.Window',
    alias: 'widget.companyInfoEditWindow',


    requires:["app.common.BasedataCheckboxGroup","app.companyInfo.companyInfoController"],

    controller:'companyInfoController',

    autoShow:true,
    modal:true,
    layout:"fit",
    buttonAlign:'center',
    buttons: [{
        text: '保存',
        handler: 'submitCompanyInfo'
    }],
    items:[{
        margin:10,
        xtype:"form",
        border:false,
        items:[{
            layout:'column',
            border:false,
            margin:'10px 0',
            items:[{
                columnWidth: 0.25,
                border:false,
                items:[{
                    xtype:'textfield',
                    labelAlign:'right',
                    fieldLabel: '公司编码',
                    width:'100%',
                    allowBlank : false,
                    name:"companyCode",
                }]
            },{
                columnWidth: 0.25,
                border:false,
                items:[{
                    xtype:'textfield',
                    labelAlign:'right',
                    fieldLabel: '公司标记',
                    width:'100%',
                    allowBlank : false,
                    name:"companyMark"
                }]
            },{
                columnWidth: 0.25,
                border:false,
                items:[{
                    xtype:'textfield',
                    labelAlign:'right',
                    fieldLabel: '公司名称',
                    width:'100%',
                    allowBlank : false,
                    name:"companyName"
                }]
            }]
        },{
            xtype: 'checkboxgroup',
            fieldLabel: '公司属性',
            labelAlign:'right',
            items: [
                {boxLabel: '运营主体', name: 'gszt', inputValue:1},
                {boxLabel: '采购主体', name: 'gszt', inputValue:2}
            ],
            width:300,
           listeners:{
                change:'onAllZtChecked'
           }
        },{
            xtype:'basedataCheckboxGroup',
            labelAlign:'right',
            //margin:'10px 0',
            fieldLabel: '运营主体',
            basedataCode:'brandInfo',
            name:"yuyingzt",
            hidden:'ture',
        },{
            xtype:'basedataCheckboxGroup',
            labelAlign:'right',
            //margin:'10px 0',
            fieldLabel: '采购主体',
            basedataCode:'brandInfo',
            name:"cgzt",
            hidden:'ture'
        },{
            xtype:'textarea',
            labelAlign:'right',
            margin:'10px 0',
            fieldLabel: '公司地址',
            width:'100%',
            height:80
        },{
            xtype:'textarea',
            labelAlign:'right',
            margin:'10px 0',
            fieldLabel: '备注',
            width:'100%',
            height:120
        }]
    }]
});