Ext.define('app.productLayout.layoutPlan.LayoutPlanController', {
    extend: 'Ext.app.ViewController',
    alias : 'controller.layoutPlanController',
    showEditWindow:function(){
        Ext.create("app.productLayout.layoutPlan.LayoutPlanEditWindow",{title:'添加',width:this.getView().getWidth(),height:this.getView().getHeight(),x:this.getView().getX(),y:this.getView().getY()});
    }

});