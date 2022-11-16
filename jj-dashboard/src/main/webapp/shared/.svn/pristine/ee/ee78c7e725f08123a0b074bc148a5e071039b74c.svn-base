var TextUtil = { UNICODE_CHAR_SIZE: 3 };

TextUtil.trim = function(value) {
    if (value == null) {
        return value;
    }

    return String(value).replace(/(^\s*)|(\s*$)/g, "");
};

TextUtil.replaceAll = function(value, sFindText, sReplaceText, bIgnoreCase) {
    if (value == null || sFindText == null) {
        return value;
    }

    return String(value).replace(new RegExp(sFindText, "g" + (bIgnoreCase == true ? "i" : "")), sReplaceText);
};

TextUtil.isEmpty = function(value) {
    return value == null || value == "";
};

TextUtil.isBlank = function(value) {
    return TextUtil.isEmpty(value) || TextUtil.trim(value) == "";
};

TextUtil.defaultIfEmpty = function(value, defaultString) {
    return TextUtil.isEmpty(value) ? defaultString : value;
};

TextUtil.defaultIfBlank = function(value, defaultString) {
    return TextUtil.isBlank(value) ? defaultString : value;
};

TextUtil.leftPad = function(value, size, padStr, truncate) {
    if (value == null) {
        return value;
    }

    value = String(value);

    if (truncate === true && value.length > size) {
        return value.substr(0, size);
    }

    while (value.length < size) {
        value = padStr + value;
    }

    return value;
};

TextUtil.rightPad = function(value, size, padStr, truncate) {
    if (value == null) {
        return value;
    }

    value = String(value);

    if (truncate === true && value.length > size) {
        return value.substr(0, size);
    }

    while (value.length < size) {
        value = value + padStr;
    }

    return value;
};

TextUtil.startsWith = function(value, prefix, ignoreCase) {
    if (value == null || prefix == null) {
        return false;
    }

    value = String(value);

    if (value.length < prefix.length) {
        return false;
    }

    if (ignoreCase == true) {
        return value.substr(0, prefix.length).toUpperCase() == prefix.toUpperCase();
    }

    return value.substr(0, prefix.length) == prefix;
};

TextUtil.endsWith = function(value, suffix, ignoreCase) {
    if (value == null || suffix == null) {
        return false;
    }

    value = String(value);

    if (value.length < suffix.length) {
        return false;
    }

    if (ignoreCase == true) {
        return value.substr(value.length - suffix.length).toUpperCase() == suffix.toUpperCase();
    }

    return value.substr(value.length - suffix.length) == suffix;
};

TextUtil.getTotalByte = function(value, unicodeCharSize) {
    if (!value || value.length == 0) {
        return 0;
    }

    var totalByte = 0;

    for (let i = 0; i < value.length; i++) {
        totalByte += (value.charCodeAt(i) <= 128 ? 1 : unicodeCharSize || TextUtil.UNICODE_CHAR_SIZE);
    }

    return totalByte;
};

TextUtil.getMaxByteLength = function(value, maxByte, unicodeCharSize) {
    if (!value || value.length == 0) {
        return maxByte;
    }

    var totalByte = TextUtil.getTotalByte(value, unicodeCharSize);

    return Math.max(maxByte - (totalByte - value.length), 0);
};

TextUtil.getTruncatedValue = function(value, maxByte, unicodeCharSize) {
    if (!value || !maxByte) {
        return value;
    }

    var totalByte = 0;
    var idx = 0;

    for (var i = 0; i < value.length; i++) {
        totalByte += (value.charCodeAt(i) <= 128 ? 1 : unicodeCharSize);

        if (totalByte <= maxByte) {
            idx = i;
        }
    }

    value = value.substring(0, idx + 1);

    if (value.substr(value.length - 1) == "\r") {
        value = value.substr(0, value.length - 1);
    }

    return value;
};

TextUtil.setTruncatedValue = function(element, maxByte, unicodeCharSize) {
    var $element = $(element);
    var value = $element.val();
    var truncatedValue = TextUtil.getTruncatedValue(value, maxByte, unicodeCharSize);

    if (value != truncatedValue) {
        $element.val(truncatedValue);
    }
};

TextUtil.resetVueElmMaxLength = function(vue) {
    if (vue.maxByte == null) {
        return;
    }

    var $elm = vue.$getText();

    $elm.attr("maxlength", TextUtil.getMaxByteLength($elm.val(), vue.maxByte, vue.unicodeCharSize));
    vue.totalByte = TextUtil.getTotalByte($elm.val(), vue.unicodeCharSize);

    if (vue.byteIndicator !== true) {
        return;
    }

    if ($elm[0].$byteIndicator == null) {
        $elm[0].$byteIndicator = $('<div style="float: right;">').insertAfter($elm);
    }

    var html = '<span style="color: ' + (vue.totalByte > vue.maxByte ? "red" : "blue") + ';">' + vue.totalByte + ' byte</span> of ' + vue.maxByte + ' byte input.';

    $elm[0].$byteIndicator.html(html);
};
