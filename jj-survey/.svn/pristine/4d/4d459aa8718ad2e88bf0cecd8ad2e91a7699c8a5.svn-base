$(function() {
    var csrfParameterName = $('meta[name="X-CSRF-Parameter-Name"]').attr("content");
    var csrfToken = $('meta[name="X-CSRF-Token"]').attr("content");

    $('form').on("submit", function() {
        var $form = $(this);

        if ($form.find('input:hidden[name="' + csrfParameterName + '"]').length == 0) {
            $('<input type="hidden" name="' + csrfParameterName + '" value="' + csrfToken + '">').appendTo($form);
        }
    });
});

function resizeIframeHeight() {
    if (window.frameElement == null) {
        return;
    }

    if (parent.$(window.frameElement).attr("auto-height") == null) {
        return;
    }

    if ($('body').is(':visible') !== true) {
        return;
    }

    var bodyHeight = $('body').prop("scrollHeight");
    var modalHeight = $('.modal').prop("scrollHeight");

    if (modalHeight == null) {
        modalHeight = 0;
    }

    $(window.frameElement).height(Math.max(bodyHeight, modalHeight));

    window.setTimeout(resizeIframeHeight, 250);
}
