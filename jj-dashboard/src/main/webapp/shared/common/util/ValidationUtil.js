var ValidationUtil = {};

ValidationUtil.init = function(vue, group) {
    if (ValidationUtil.validators == null) {
        return;
    }

    if (ValidationUtil.validators[vue.uuid] == null) {
        return;
    }

    if (group == null) {
        for (var group in ValidationUtil.validators[vue.uuid]) {
            init(group);
        }
    } else {
        init(group);
    }

    function init(group) {
        if (ValidationUtil.validators[vue.uuid][group] == null) {
            return;
        }

        for (var i = 0; i < ValidationUtil.validators[vue.uuid][group].length; i++) {
            ValidationUtil.validators[vue.uuid][group][i].initValid();
        }
    }
};

ValidationUtil.check = function(vue, group) {
    if (ValidationUtil.validators == null) {
        return true;
    }

    if (ValidationUtil.validators[vue.uuid] == null) {
        return;
    }

    var invalidCount = 0;

    if (group == null) {
        for (var group in ValidationUtil.validators[vue.uuid]) {
            invalidCount += check(group);
        }
    } else {
        invalidCount += check(group);
    }

    if (invalidCount > 0) {
        alert("유효하지 않은 입력값이 " + invalidCount + "개 있습니다.");
    }

    return invalidCount === 0;

    function check(group) {
        if (ValidationUtil.validators[vue.uuid][group] == null) {
            return 0;
        }

        var invalidCount = 0;

        for (var i = 0; i < ValidationUtil.validators[vue.uuid][group].length; i++) {
            if (ValidationUtil.validators[vue.uuid][group][i].checkValid() != true) {
                invalidCount++;
            }
        }

        return invalidCount;
    }
};

ValidationUtil.addValid = function(checkerVue) {
    var vue = checkerVue.$root;

    if (ValidationUtil.validators == null) {
        ValidationUtil.validators = {};
    }

    if (ValidationUtil.validators[vue.uuid] == null) {
        ValidationUtil.validators[vue.uuid] = {};
    }

    var groups;

    if (checkerVue.groups == null) {
        groups = ["_ALL_"];
    } else {
        groups = checkerVue.groups.split(",");
    }

    for (var i = 0; i < groups.length; i++) {
        var group = $.trim(groups[i]);

        if (ValidationUtil.validators[vue.uuid][group] == null) {
            ValidationUtil.validators[vue.uuid][group] = [];
        }

        ValidationUtil.validators[vue.uuid][group].push(checkerVue);
    }

    ValidationUtil.setRequiredClass(checkerVue, true);
};

ValidationUtil.removeValid = function(checkerVue) {
    var vue = checkerVue.$root;

    if (ValidationUtil.validators == null) {
        return;
    }

    if (ValidationUtil.validators[vue.uuid] == null) {
        ValidationUtil.validators[vue.uuid] = {};
    }

    for (var group in ValidationUtil.validators[vue.uuid]) {
        for (var i = ValidationUtil.validators[vue.uuid][group].length - 1; i >= 0; i--) {
            if (ValidationUtil.validators[vue.uuid][group][i] === checkerVue) {
                ValidationUtil.validators[vue.uuid][group].splice(i, 1);
            }
        }
    }

    ValidationUtil.setRequiredClass(checkerVue, false);
};

ValidationUtil.setRequiredClass = function(checkerVue, add) {
    if (checkerVue.tableHeader == null) {
        return;
    }

    var required = (function() {
        if (checkerVue.check == null) {
            return false;
        }

        var checks = checkerVue.check.split(",");

        for (var i = 0; i < checks.length; i++) {
            var check = $.trim(checks[i]);

            if (check == "required") {
                return true;
            }
        }

        return false;
    })();

    if (required !== true) {
        return;
    }

    if (add === true) {
        $(checkerVue.tableHeader).addClass("required");
    } else {
        $(checkerVue.tableHeader).removeClass("required");
    }
};

ValidationUtil.validator = {
    "required": function(value) {
        var valid;

        if (value == null) {
            valid = false;
        } else if ($.type(value) === "boolean") {
            valid = true;
        } else if ($.type(value) === "array") {
            for (var i = 0; i < value.length; i++) {
                if (value[i]._JOB_TYPE != "D") {
                    valid = true;
                    break;
                }
            }
        } else {
            valid = (value != "");
        }

        if (valid !== true) {
            return "필수 항목입니다.";
        }
    },
    "number": function(value) {
        if (value == null || (value + "").length === 0) {
            return;
        }

        if (isNaN(value) === true) {
            return "숫자 형식이 올바르지 않습니다.";
        }
    },
    "email": function(value) {
        if (value == null || (value + "").length === 0) {
            return;
        }

        var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

        if (regExp.test(value) !== true) {
            return "이메일주소 형식이 올바르지 않습니다.";
        }
    },
    "phone": function(value) {
        if (value == null || (value + "").length === 0) {
            return;
        }

        var regExp = /^\d{2,3}-\d{3,4}-\d{4}$/;

        if (regExp.test(value) !== true) {
            return "전화번호가 올바르지 않습니다. 하이픈(-)을 포함한 숫자만 입력하세요.";
        }
    },
    "cell-phone": function(value) {
        if (value == null || (value + "").length === 0) {
            return;
        }

        var regExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;

        if (regExp.test(value) !== true) {
            return "휴대폰번호가 올바르지 않습니다. 하이픈(-)을 포함한 숫자만 입력하세요.";
        }
    },
    _unknown: function(value) {
        return "값 유효성을 확인할 수 없습니다.";
    }
};
