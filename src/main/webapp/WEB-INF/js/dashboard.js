//On load
$(function() {
    // Default: hide edit mode
    $(".editMode").hide();
    
    // Click on "selectall" box
    $("#selectall").click(function () {
        $('.cb').prop('checked', this.checked);
    });

    // Click on a checkbox
    $(".cb").click(function() {
        if ($(".cb").length == $(".cb:checked").length) {
            $("#selectall").prop("checked", true);
        } else {
            $("#selectall").prop("checked", false);
        }
        if ($(".cb:checked").length != 0) {
            $("#deleteSelected").attr("enabled");
        } else {
            $("#deleteSelected").attr("disabled");
        }
    });

});


// Function setCheckboxValues
(function ( $ ) {

    $.fn.setCheckboxValues = function(formFieldName, checkboxFieldName) {

        var str = $('.' + checkboxFieldName + ':checked').map(function() {
            return this.value;
        }).get().join();
        
        $(this).attr('value',str);
        
        return this;
    };

}( jQuery ));

// Function toggleEditMode
(function ( $ ) {

    $.fn.toggleEditMode = function() {
        if($(".editMode").is(":visible")) {
            $(".editMode").hide();
            $("#editComputer").text("Edit");
        }
        else {
            $(".editMode").show();
            $("#editComputer").text("View");
        }
        return this;
    };

}( jQuery ));


// Function delete selected: Asks for confirmation to delete selected computers, then submits it to the deleteForm
(function ( $ ) {
    $.fn.deleteSelected = function() {
        if (confirm("Are you sure you want to delete the selected computers?")) { 
            $('#deleteForm input[name=selection]').setCheckboxValues('selection','cb');
            $('#deleteForm').submit();
        }
    };
}( jQuery ));



//Event handling
//Onkeydown
$(document).keydown(function(e) {

    switch (e.keyCode) {
        //DEL key
        case 46:
            if($(".editMode").is(":visible") && $(".cb:checked").length != 0) {
                $.fn.deleteSelected();
            }   
            break;
        //E key (CTRL+E will switch to edit mode)
        case 69:
            if(e.ctrlKey) {
                $.fn.toggleEditMode();
            }
            break;
    }
});

$('#previous').click(function(e){
	var page = parseInt(getCurrentPage()) - 1;
	if(page < 1){
		page = 1;
	}
	paginatePage("page=" + page)
});

$('#next').click(function(e){
	var page = parseInt(getCurrentPage()) + 1;
	paginatePage("page=" + page)
});

function getCurrentPage(){
	var page = 1;
	var searchParams = new URLSearchParams(window.location.search)
	if(searchParams.has('page')){
		page = searchParams.get('page');
	}
	return page;
}
$('#show100').click(function(e){
	paginateShow("show=100")
});
$('#show50').click(function(e){
	paginateShow("show=50")
});
$('#show10').click(function(e){
	paginateShow("show=10")
});

$('.page').click(function(e){
	var id = $(this).attr("id");
	console.log(id);
	paginatePage("page=" + id);
});

function paginatePage(addfield){
	var href = window.location.href;
	var newHref;
	if (href.indexOf('?') !== -1) {
		if(href.includes("page=")){
			newHref = href.replace(/page=[0-9]{1,4}/, addfield);
		}else{
			newHref = href + addfield;
		}
	}else{
		newHref = href + '?' + addfield;
	}
	window.location.href = newHref;
}

function paginateShow(addfield){
	var href = window.location.href;
	var newHref;
	if (href.indexOf('?') !== -1) {
		if(href.includes("&show=")){
			newHref = href.replace(/&show=[0-9]{1,4}/, "&" + addfield);
		}else{
			newHref = href + '&' + addfield;
		}
	}else{
		newHref = href + '?' + addfield;
	}
	window.location.href = newHref;
}