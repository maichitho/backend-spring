$("#header-search .nav-right .btn-user img").hover(function(){
    $(this).attr("src","img/user-icon-hover.png");
}, function(){
    $(this).attr("src","img/user-icon.png");
});
$("#header-search .nav-right .btn-cart img").hover(function(){
    $(this).attr("src","img/cart-icon-hover.png");
}, function(){
    $(this).attr("src","img/cart-icon.png");
});
var setBtnMenu = true;
$("#header-btn-menu").click(function(){
    $(".header-menu").toggle();
    if(setBtnMenu){
        $("#header-btn-menu img").attr("src","img/cancel-icon.png");
        setBtnMenu = !setBtnMenu;
    }else{
        $("#header-btn-menu img").attr("src","img/menu-icon.png");
        setBtnMenu = !setBtnMenu;
    }
});
$("#btn-get-app").click(function(){
    $("#header-search .header-get-app").toggle();
});
$("#menu-get-app").click(function(){
    $("#header-search .header-get-app").toggle();
});
$("#btn-language").click(function(){
    $("#header-search .header-language").toggle();
});
$("#menu-language").click(function(){
    $("#header-search .header-language").toggle();
})
