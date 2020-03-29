<script type="text/javascript">
    $(function() {
        $('.nav-control').click(function() {
            if ($(this).siblings('.nav-list').css('display') == "block") {
                $(this).siblings('.nav-list').slideUp(300);
                return false;
            }
            $('.nav-control').removeClass('active');
            $(this).addClass('active');
            $(this).siblings('.nav-list').slideDown(300);
        });
        $('.nav-list a').click(function() {
            $('.nav-list a').css({"background": "#29282a", "color": "#939393"})
            $('.nav-list a').find('img').hide();
            $(this).css({"background": "#3d85cc", "color": "#ffffff"});
            $(this).find('img').show();
        });
    });
</script>

</html>