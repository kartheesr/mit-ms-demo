$(document).ready(function(){
	
    $('a').click(function(event){event.preventDefault();})
    let stepscount = $('#workflow-steps li').length;
    let progressbarlength = $('#workflow-line').width() / (stepscount-1);
    alert(progressbarlength);
    let activelist = 0;
    $('.msg-box').html($('.workflow-steps .msg').first().html());
    $('.workflow-steps').first().find('img').css({'top':'-60px','transform':'rotate(-360deg)', 'display':'block' });

    $('#workflow-steps li').click(function(){
    	
        $('.msg-box').addClass('animate');
        $(this).addClass('active');
        $('.left-animated-arrow').fadeOut(500);
        activelist = $(this).index()
        $('.progress').css('width',activelist*progressbarlength + 'px');
        $('#workflow-steps li').find('img').css({ 'top':'10px', 'transform':'rotate(0deg)','display':'block'});
        $(this).find('img').css({'top':'-60px','transform':'rotate(-360deg)', 'display':'block' });
       
        $('.msg-box').html($(this).find('.msg').html());


        $('.msg-box').one('webkitAnimationEnd oanimationend msAnimationEnd animationend',function(e) {
          $('.msg-box').removeClass('animate');
        });





    });
});