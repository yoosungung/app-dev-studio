var CommonUtil = {};

(function() {
    if (window.axios != null) {
        axios.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";

        var csrfHeaderName = $('meta[name="X-CSRF-Header-Name"]').attr("content");
        var csrfToken = $('meta[name="X-CSRF-Token"]').attr("content");

        if (csrfHeaderName != "" && csrfToken != "") {
            axios.defaults.headers.common[csrfHeaderName] = csrfToken;
        }
    }
})();

(function() {
    if (window.$ == null || $.fn.dialog == null) {
        return;
    }

    $.fn.dialog_origin = $.fn.dialog;

    $.fn.extend({
        dialog: function() {
            if ($(this).find('input#_dummyTextBox_').length == 0) {
                $('<input>').attr("id", "_dummyTextBox_").css({ "position": "absolute", "top": "-1000px" }).prependTo(this);
            }

            if (arguments.length > 0 && $.type(arguments[0]) == "object") {
                var open_origin = arguments[0].open;
                var close_origin = arguments[0].close;

                var scrollTop;
                var scrollLeft;

                if ($('body').css("overflow") != "hidden") {
                    scrollTop = (window.scrollY || document.documentElement.scrollTop);
                    scrollLeft = (window.scrollX || document.documentElement.scrollLeft);
                }

                arguments[0].open = function(event, ui) {
                    if (scrollTop != null || scrollLeft != null) {
                        $('body').css("overflow", "hidden").scrollTo({ top: scrollTop, left: scrollLeft });
                    }

                    $(this).find('[auto-focus]').focus();

                    if (open_origin != null) {
                        open_origin.apply(this, arguments);
                    }
                }

                arguments[0].close = function(event, ui) {
                    if (scrollTop != null || scrollLeft != null) {
                        $('body').css("overflow", "auto");
                    }

                    if (close_origin != null) {
                        close_origin.apply(this, arguments);
                    }
                }
            }

            return this.dialog_origin.apply(this, arguments);
        }
    });
})();

CommonUtil.contextPath = (function() {
    if (window.$ != null) {
        return $('meta[name="X-Context-Path"]').attr("content");
    }
})();

CommonUtil.axios = function(option) {
    var _axios = axios.create({
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (option === undefined || option.progressBar === undefined || option.progressBar) {
        if (window.loadProgressBar != null) {
            loadProgressBar({ showSpinner: true }, _axios);
        }
    }

    if (option && option.original) {
        return _axios;
    }

    _axios.interceptors.response.use(undefined, function(error) {
        if (error.config && error.response && error.response.headers["x-invalid-token"] === "true") {
            var csrfHeaderName = $('meta[name="X-CSRF-Header-Name"]').attr("content");

            axios.defaults.headers.common[csrfHeaderName] = error.response.headers[csrfHeaderName.toLowerCase()];

            error.config.headers[csrfHeaderName] = error.response.headers[csrfHeaderName.toLowerCase()];
            error.config.headers[csrfHeaderName + "-Retry"] = "true";

            $('meta[name="X-CSRF-Token"]').attr("content", error.config.headers[csrfHeaderName]);

            return _axios.request(error.config);
        }

        return Promise.reject(error);
    });

    return {
        get: function(url, config) {
            if (config === undefined) {
                config = {};
            }

            if (config.headers === undefined) {
                config.headers = {};
            }

            config.headers["X-HTTP-Method-Override"] = "GET";

            if (config.data === undefined) {
                config.data = null;
            }

            return _axios.post(getContextUrl(url), config.data, config);
        },
        post: function(url, data, config) {
            return _axios.post(getContextUrl(url), data, config);
        },
        put: function(url, data, config) {
            if (config === undefined) {
                config = {};
            }

            if (config.headers === undefined) {
                config.headers = {};
            }

            config.headers["X-HTTP-Method-Override"] = "PUT";

            return _axios.post(getContextUrl(url), data, config);
        },
        delete: function(url, config) {
            if (config === undefined) {
                config = {};
            }

            if (config.headers === undefined) {
                config.headers = {};
            }

            config.headers["X-HTTP-Method-Override"] = "DELETE";

            if (config.data === undefined) {
                config.data = null;
            }

            return _axios.post(getContextUrl(url), config.data, config);
        }
    };

    function getContextUrl(url) {
        if (url == null || url === "") {
            return window.location.pathname;
        }

        if (url.substr(0, 1) === "/") {
            return CommonUtil.contextPath + url;
        }

        return url;
    }
};

CommonUtil.enableHashRouter = function(defaultVueName) {
    CommonUtil.enableHashRouter.defaultVueName = defaultVueName;

    $(window).on("hashchange", function() {
        CommonUtil.changeHashRouter();
    });

    if (window.location.hash == "") {
        CommonUtil.changeHashRouter("#" + defaultVueName);
    } else {
        CommonUtil.changeHashRouter();
    }
};

CommonUtil.changeHashRouter = function() {
    var hash = window.location.hash;
    var vueName;
    var methodName;

    if (hash == "") {
        vueName = CommonUtil.enableHashRouter.defaultVueName;
    } else if (hash.indexOf(":") != -1) {
        vueName = hash.substr(1, hash.indexOf(":") - 1);
    } else {
        vueName = hash.substr(1);
    }

    if (vueName.indexOf(".") != -1) {
        methodName = vueName.substr(vueName.indexOf(".") + 1);
        vueName = vueName.substr(0, vueName.indexOf("."));
    } else {
        methodName = "init";
    }

    for (var name in vues) {
        if (vues[name].$el != null) {
            $(vues[name].$el).hide();
        }
    }

    var vue = vues[vueName];
    var $view;

    if (vue == null) {
        $view = $('#' + vueName);
    } else {
        if (vue[methodName] != null) {
            var params;

            if (hash.indexOf(":") != -1) {
                params = hash.substr(hash.indexOf(":") + 1).split(":");
            }

            vue[methodName].apply(vue, params);
        }

        $('.pageCon').scrollTop();

        $view = $(vue.$el);
    }

    $view.show().find('[auto-focus]').focus();

    return vue;
};

CommonUtil.changeVue = function() {
    var params = Array.prototype.slice.call(arguments);

    if (params.length == 1 && params[0] == CommonUtil.enableHashRouter.defaultVueName) {
        history.pushState(null, document.title, window.location.pathname + window.location.search);
        CommonUtil.changeHashRouter();
    } else {
        window.location.href = "#" + params.join(":");
    }
};

CommonUtil.replaceVue = function() {
    var params = Array.prototype.slice.call(arguments);

    if (params.length == 1 && params[0] == CommonUtil.enableHashRouter.defaultVueName) {
        history.replaceState(null, document.title, window.location.pathname + window.location.search);
    } else {
        history.replaceState(null, document.title, "#" + params.join(":"));
    }

    CommonUtil.changeHashRouter();
};

CommonUtil.popupVue = function(vueName) {
    var methodName;

    if (vueName.indexOf(".") != -1) {
        methodName = vueName.substr(vueName.indexOf(".") + 1);
        vueName = vueName.substr(0, vueName.indexOf("."));
    } else {
        methodName = "init";
    }

    var vue = vues[vueName];
    var $view;

    if (vue == null) {
        $view = $('#' + vueName);
    } else {
        if (vue[methodName] != null) {
            var params = Array.prototype.slice.call(arguments);

            params.splice(0, 1);

            vue[methodName].apply(vue, params);
        }

        $view = $(vue.$el);
    }

    $view.find('[auto-focus]').focus();
};

CommonUtil.initModel = function(model) {
    for (var key in model) {
        var type = $.type(model[key]);

        if (type === "array") {
            model[key] = [];
        } else if (type === "object") {
            model[key] = {};
        } else {
            model[key] = null;
        }
    }
};

CommonUtil.showAxiosError = function(error) {
    if (!error.response) {
        window.alert(error);
        return;
    }

    var invalidSession = false;
    var invalidToken = false;
    var message;

    if (!error.response) {
        message = error;
    } else {
        invalidSession = (error.response.headers["x-invalid-session"] === "true");
        invalidToken = (error.response.headers["x-invalid-token"] === "true");

        if (invalidSession) {
            message = "오랫동안 사용하지 않아 로그아웃 되었습니다.";
        } else if (invalidToken) {
            message = "유효하지 않은 접근정보입니다.";
        } else {
            message = error.response.data.message;
        }
    }

    window.alert(message);

    if (invalidSession || invalidToken) {
        window.location.reload();
    }
};

CommonUtil.copyObjectValues = function(source, target) {
    if (source == null || target == null) {
        return;
    }

    for (var key in source) {
        target[key] = source[key];
    }
};

CommonUtil.toDate = function(value) {
    if (value == null) {
        return;
    }

    var type = $.type(value);

    if (type === "date") {
        return value;
    }

    if (type === "number") {
        return new Date(value);
    }

    if (type === "string") {
        if (value.length === 10) {
            var separator1 = value.substr(4, 1);
            var separator2 = value.substr(7, 1);

            return new Date(moment(value, "YYYY" + separator1 + "MM" + separator2 + "DD"));
        }

        if (value.length === 8) {
            return new Date(moment(value, "YYYYMMDD"));
        }
    }
};

CommonUtil.setJobType = function(data, jobType) {
    if ($.type(data) === "array") {
        for (var i = 0; i < data.length; i++) {
            data[i]._JOB_TYPE = jobType;
        }
    } else {
        data._JOB_TYPE = jobType;
    }

    return data;
};

CommonUtil.getSortedList = function(data, sortKey) {
    return data.sort(function(a, b) {
        var aValue = a[sortKey];
        var bValue = b[sortKey];

        if (aValue != null && bValue != null) {
            return Number(aValue) - Number(bValue);
        }

        return 0;
    });
};

CommonUtil.getFilteredList = function(data, sortKey) {
    var result;

    if (sortKey == null) {
        result = data;
    } else {
        for (var i = 0; i < data.length; i++) {
            if (data[i]._JOB_TYPE == "D") {
                data[i][sortKey] = 0;
            }
        }

        result = CommonUtil.getSortedList(data, sortKey);
    }

    return result.filter(function(rowData) {
        return rowData._JOB_TYPE != "D";
    });
};

CommonUtil.setListSortOrder = function(data, sortKey, row, direction) {
    if (row[sortKey] + direction == 0) {
        return;
    }

    if (row[sortKey] + direction > data.length) {
        return;
    }

    for (var i = 0; i < data.length; i++) {
        if (data[i][sortKey] == row[sortKey] + direction) {
            data[i][sortKey] = data[i][sortKey] + (direction * (-1))
            break;
        }
    }

    row[sortKey] = row[sortKey] + direction;
};

CommonUtil.formatter = {
    date: function(value, format) {
        var date = CommonUtil.toDate(value);

        if (date != null) {
            return moment(date).format(format || "YYYY-MM-DD");
        }
    },
    time: function(value, format) {
        var date = CommonUtil.toDate(value);

        if (date != null) {
            return moment(date).format(format || "HH:mm:ss");
        }
    },
    datetime: function(value, format) {
        var date = CommonUtil.toDate(value);

        if (date != null) {
            return moment(date).format(format || "YYYY-MM-DD HH:mm:ss");
        }
    },
    number: function(value, digits, thousandsSeparator) {
        if (value == null) {
            return value;
        }

        value = String(value);

        if (value == "") {
            return value;
        }

        if (arguments.length < 3) {
            thousandsSeparator = ",";
        }

        if (thousandsSeparator == null) {
            thousandsSeparator = "";
        }

        if (thousandsSeparator != "") {
            value = TextUtil.replaceAll(value, thousandsSeparator, "");
        }

        if (!isNaN(digits)) {
            value = String(Math.round(parseFloat(value, 10) * Math.pow(10, digits)) / Math.pow(10, digits));
        }

        var minus = (value.substr(0, 1) == "-");
        var values = (minus == true ? value.substr(1) : value).split(".");
        var fixed = TextUtil.defaultIfBlank(values[0], "0").replace(new RegExp(thousandsSeparator, "g"), "").split("").reverse().join("").match(/.{1,3}/g).join(thousandsSeparator).split("").reverse().join("");
        var decimal = TextUtil.rightPad(values[1] == null ? "" : values[1], digits, "0");

        return (minus == true ? "-" : "") + fixed + (decimal == "" ? "" : ".") + decimal;
    }
};

CommonUtil.loadCodeData = function(requestCodeList, callback, update) {
    if (CommonUtil.loadCodeData.loadedCodeDataMap == null) {
        CommonUtil.loadCodeData.loadedCodeDataMap = {};
    }

    if ($.type(requestCodeList) === "string") {
        requestCodeList = [{ key: requestCodeList }];
    }

    var result = {};

    if (update !== true) {
        for (var i = requestCodeList.length - 1; i >= 0; i--) {
            var key = requestCodeList[i].key;

            if (CommonUtil.loadCodeData.loadedCodeDataMap[key] != null) {
                result[key] = CommonUtil.loadCodeData.loadedCodeDataMap[key];
                requestCodeList.splice(i, 1);
            }
        }
    }

    if (requestCodeList.length == 0) {
        if (callback != null) {
            callback(result);
        }

        return;
    }

    CommonUtil.axios()
    .post("/common/codedata/CodeData", requestCodeList)
    .then(function(response) {
        for (var key in response.data) {
            var data = response.data[key];
            var codeData;

            if (data.error == true) {
                codeData = { list: [] };
                alert(data.message);
            } else {
                codeData = data;
            }

            CommonUtil.loadCodeData.loadedCodeDataMap[key] = codeData;
            result[key] = codeData;
        }

        if (callback != null) {
            callback(result);
        }
    })["catch"](function(error) {
        CommonUtil.showAxiosError(error);
    });
};

CommonUtil.updateCodeData = function(requestCodeList, callback) {
    if (requestCodeList == null) {
        requestCodeList = [];

        if (CommonUtil.loadCodeData.loadedCodeDataMap != null) {
            for (var key in CommonUtil.loadCodeData.loadedCodeDataMap) {
                requestCodeList.push({ "key": key });
            }
        }
    }

    CommonUtil.loadCodeData(requestCodeList, callback, true);
};

CommonUtil.downloadTempFile = function(tempFileName, realFileName) {
    var formName = "tempFileDownloadForm_" + tempFileName;
    var iframeName = "tempFileDownloadFrame_" + tempFileName;

    var $form = $('form[name="' + formName + '"]');
    var $iframe = $('iframe[name="' + iframeName + '"]');

    if ($form.length == 0) {
        $form = $('<form>').attr({ name: formName }).hide().appendTo('body');
    }

    if ($iframe.length == 0) {
        $iframe = $('<iframe>').attr({ name: iframeName }).hide().appendTo('body');
    }

    if (realFileName == null) {
        realFileName = $('.page-header > h1').text();
    }

    $form.attr("target", $iframe.attr("name"));
    $form.attr("method", "POST");
    $form.attr("action", CommonUtil.contextPath + "/common/file/downloadTempFile");
    $form.append($('<input type="hidden" name="' + $('meta[name="X-CSRF-Parameter-Name"]').attr("content") + '">').val($('meta[name="X-CSRF-Token"]').attr("content")));
    $form.append($('<input type="hidden" name="tempFileName">').val(tempFileName));
    $form.append($('<input type="hidden" name="realFileName">').val(realFileName));
    $form.submit();
};

CommonUtil.getRootWindow = function(win) {
    if (win == null) {
        win = window;
    }

    try {
        if (win !== win.parent) {
            return CommonUtil.getRootWindow(win.parent);
        }
    } catch(e) {
    }

    return win;
};

CommonUtil.getUid = function() {
    var callback
    var type;

    for (var i = 0; i < arguments.length; i++) {
        if (typeof(arguments[i]) === "function") {
            callback = arguments[i];
        } else {
            type = arguments[i];
        }
    }

    CommonUtil.axios()
    .get("/infrastructure/idgen/" + (type == null ? "" : "?type=" + type))
    .then(function(response) {
        if (callback != null) {
            callback(response.data);
        }
    })["catch"](function(error) {
        CommonUtil.showAxiosError(error);
    });
};

CommonUtil.uploadFiles = function(vue, callback) {
    if (vue.FilePondVues == null) {
        if (callback != null) {
            callback();
        }

        return;
    }

    for (var i = 0; i < vue.FilePondVues.length; i++) {
        vue.FilePondVues[i].upload(uploadCallback);
    }

    function uploadCallback(vue) {
        for (var i = 0; i < vue.FilePondVues.length; i++) {
            if (vue.FilePondVues[i].isAllFilesUploaded() !== true) {
                return;
            }
        }

        if (callback != null) {
            callback();
        }
    }
};

(function(a){(jQuery.browser=jQuery.browser||{}).mobile=/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino/i.test(a)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0,4))})(navigator.userAgent||navigator.vendor||window.opera);