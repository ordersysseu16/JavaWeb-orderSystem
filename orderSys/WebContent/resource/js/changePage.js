$(document).ready(function(){
  var random = parseInt(100*Math.random());

  // $("a.logo").click(function(event) {
  //   $("iframe").attr("src", $(this).attr("href")+"?noCache="+random);
  //   $("a.current").removeClass("current");
  //   event.preventDefault();
  // });

  $("a.categories").click(function(event) {
    $("iframe").attr("src", $(this).attr("href")+"?noCache="+random);
    $("a.current").removeClass("current");
    $(this).addClass("current");
    event.preventDefault();
  });

  $("a.categories").eq(0).trigger("click");
});
