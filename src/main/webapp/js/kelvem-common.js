


function keepTop(jquery_select, offset_top) {

	var $sideBar = $(jquery_select);
	$sideBar.css("top", offset_top);
	$sideBar.css("position", "fixed");

	// back to top
	setTimeout(function() {
		var $sideBar = $(jquery_select)

		//$sideBar.affix.offset.top = 200px;

		$sideBar.affix({
			offset : {
				top : function() {
					var offsetTop = $sideBar.offset().top
					var sideBarMargin = parseInt($sideBar.children(0).css('margin-top'), 10)

					return (this.top = offsetTop - sideBarMargin)
				}
			}
		})
	}, 100);
};
//keepTop('#menu_level2', '70px');