Vue.newComponentIndex = function() {
    if (Vue.newComponentIndexValue == null) {
        Vue.newComponentIndexValue = 0;
    }

    return ++Vue.newComponentIndexValue;
};

Vue.use(function(Vue) {
    var uuid = 0;

    Vue.mixin({
        beforeCreate: function() {
            this.uuid = "uid-" + uuid;

            uuid++;
        }
    });

    Vue.prototype.uid = function(id) {
        if (id != null) {
            return this.uuid + "-" + id;
        }

        return this.uuid;
    };
});

Vue.component('ui-button', {
    props: {
        type: {
            type: String
        },
        disabled: {
            type: Boolean
        }
    },
    template: ' \
        <button v-bind:type="type || \'button\'" v-bind:disabled="disabled" v-on:click="$emit(\'click\');"> \
            <slot></slot> \
        </button> \
    '
});

Vue.component('ui-input', {
    props: {
        value: {
            default: ""
        },
        maxByte: {
            type: Number
        },
        byteIndicator: {
            type: Boolean
        },
        unicodeCharSize: {
            type: Number,
            default: 3
        },
        fill: {
            type: Boolean,
            default: true
        }
    },
    template: ' \
        <input v-model="modelValue" v-bind:class="{ fill: fill == true }"></input> \
    ',
    data: function() {
        return {
            totalByte: 0
        };
    },
    computed: {
        modelValue: {
            get: function() {
                return this.value;
            },
            set: function(value) {
                this.$emit("input", value);
            }
        }
    },
    mounted: function() {
        var vue = this;

        vue.$getText().on("keyup", function() {
            TextUtil.setTruncatedValue(this, vue.maxByte, vue.unicodeCharSize);

            vue.resetMaxLength();
        });
    },
    updated: function() {
        this.resetMaxLength();
    },
    methods: {
        $getText: function() {
            return $(this.$el);
        },
        resetMaxLength: function() {
            TextUtil.resetVueElmMaxLength(this);
        }
    }
});

Vue.component('ui-textarea', {
    props: {
        value: {
            default: ""
        },
        maxByte: {
            type: Number
        },
        byteIndicator: {
            type: Boolean
        },
        unicodeCharSize: {
            type: Number,
            default: 3
        },
        rows: {
            type: String
        },
        fill: {
            type: Boolean,
            default: true
        }
    },
    template: ' \
        <textarea v-model="modelValue" v-bind:rows="rows" v-bind:class="{ fill: fill == true }"></textarea> \
    ',
    data: function() {
        return {
            totalByte: 0
        };
    },
    computed: {
        modelValue: {
            get: function() {
                return this.value;
            },
            set: function(value) {
                this.$emit("input", value);
            }
        }
    },
    mounted: function() {
        var vue = this;

        vue.$getText().on("keyup", function() {
            TextUtil.setTruncatedValue(this, vue.maxByte, vue.unicodeCharSize);

            vue.resetMaxLength();
        });
    },
    updated: function() {
        this.resetMaxLength();
    },
    methods: {
        $getText: function() {
            return $(this.$el);
        },
        resetMaxLength: function() {
            TextUtil.resetVueElmMaxLength(this);
        }
    }
});

Vue.component('ui-date', {
    props: {
        value: {
        },
        displayFormat: {
            type: String,
            default: "YYYY-MM-DD"
        },
        type: {
            type: String,
            default: "long"
        },
        disabled: {
            type: Boolean
        }
    },
    template: ' \
        <div style="display: inline;"> \
            <input type="text" v-model="modelValue" readonly class="tac" v-bind:disabled="disabled === true" style="width: 100px;"></input> \
            <div class="icon-date"></div> \
        </div> \
    ',
    computed: {
        modelValue: {
            get: function() {
                return CommonUtil.formatter.date(this.value, this.displayFormat);
            },
            set: function(value) {
                this.$emit("input", this.getReturnValue(value));
            }
        }
    },
    watch: {
        value: function() {
            this.$emit("input", this.getReturnValue(this.value));
        }
    },
    mounted: function() {
        var vue = this;
        var $elm = $(vue.$el);

        $elm.find('.icon-date').click(function() {
            if (vue.disabled != true) {
                $(this).parent().find('input').focus();
            }
        });

        $elm.find('input').daterangepicker({
            "singleDatePicker": true,
            "autoApply": true,
            locale: {
                format: vue.displayFormat
            }
        }).on("change", function() {
            vue.$emit("input", vue.getReturnValue(this.value));
        }).val(vue.modelValue);

        $elm.find('input').on("keyup", function(event) {
            if (event.keyCode != 8 && event.keyCode != 46) {
                return;
            }

            var $this = $(this);
            var daterangepicker = $this.data("daterangepicker");

            daterangepicker.setStartDate(0);
            daterangepicker.hide();

            $this.val("");

            vue.$emit("input", null);
        });
    },
    methods: {
        getReturnValue: function(value) {
            if (value == null) {
                return value;
            }

            var date = CommonUtil.toDate(value, this.displayFormat);

            if (this.type === "string") {
                return CommonUtil.formatter.date(date, "YYYYMMDD");
            }

            if (this.type === "date") {
                return date;
            }

            return date.getTime();
        }
    }
});

Vue.component('ui-radios', {
    props: {
        value: {
            default: ""
        },
        codeData: {
            type: String
        },
        firstName: {
            type: String
        },
        inline: {
            type: Boolean
        }
    },
    template: ' \
        <div v-bind:style="{ display: inline ? \'inline\' : null }"> \
            <label v-for="(item, index) in codeList" v-bind:key="item.code"> \
                <input type="radio" v-bind:name="radioGroupName" v-bind:value="item.code" v-model="modelValue"></input> \
                <span>{{ item.name }}</span>&nbsp; \
            </label> \
        </div> \
    ',
    data: function() {
        return {
            radioGroupName: "radioGroup" + Vue.newComponentIndex(),
            codeList: []
        };
    },
    computed: {
        modelValue: {
            get: function() {
                if (this.value == null) {
                    var $radio = $(this.$el).find('input:radio');

                    if ($radio.length > 0 && $radio.eq(0).val() == "") {
                        return "";
                    }
                }

                return this.value;
            },
            set: function(value) {
                if (value === "") {
                    this.$emit("input", null);
                } else {
                    this.$emit("input", value);
                }
            }
        }
    },
    watch: {
        value: function() {
            this.$emit("input", this.value);
        }
    },
    mounted: function() {
        if (this.codeData == null) {
            return;
        }

        var vue = this;

        CommonUtil.loadCodeData([
            { key: vue.codeData }
        ], function(codeDataMap) {
            vue.codeList = (function(codeList) {
                var list = [];

                if (vue.firstName != null) {
                    list.push({ code: "", name: vue.firstName });
                }

                for (var i = 0; i < codeList.length; i++) {
                    list.push(codeList[i]);
                }

                return list;
            })(codeDataMap[vue.codeData].list);
        });
    }
});

Vue.component('ui-select', {
    props: {
        value: {
            default: ""
        },
        codeData: {
            type: String
        },
        firstName: {
            type: String
        },
        firstDisabled: {
            type: Boolean
        },
        defaultIndex: {
            type: Number
        },
        fill: {
            type: Boolean,
            default: true
        }
    },
    template: ' \
        <select v-model="modelValue" v-bind:class="{ fill: fill == true }"> \
            <option v-for="(item, index) in codeList" v-bind:key="item.code" v-bind:value="item.code" \
                v-bind:disabled="firstDisabled === true && index === 0" \
            >{{ item.name }}</option> \
        </select> \
    ',
    data: function() {
        return {
            codeList: []
        };
    },
    computed: {
        modelValue: {
            get: function() {
                if (this.value != null) {
                    return this.value;
                }

                var $option = $(this.$el).children('option');

                if ($option.length > 0 && $option.eq(0).val() == "") {
                    return "";
                }

                if (this.defaultIndex != null) {
                    var defaultValue = $option.eq(this.defaultIndex).val();

                    this.$emit("input", defaultValue);

                    return defaultValue;
                }
            },
            set: function(value) {
                if (value === "") {
                    this.$emit("input", null);
                } else {
                    this.$emit("input", value);
                }
            }
        }
    },
    watch: {
        value: function() {
            this.$emit("input", this.value);
        },
        codeData: function() {
            this.$emit("input", "");
            this.loadCodeData();
        }
    },
    mounted: function() {
        this.loadCodeData();
    },
    methods: {
        loadCodeData: function() {
            if (this.codeData == null) {
                return;
            }

            var vue = this;

            CommonUtil.loadCodeData([
                { key: vue.codeData }
            ], function(codeDataMap) {
                vue.codeList = (function(codeList) {
                    var list = [];

                    if (vue.firstName != null) {
                        list.push({ code: "", name: vue.firstName });
                    }

                    for (var i = 0; i < codeList.length; i++) {
                        list.push(codeList[i]);
                    }

                    return list;
                })(codeDataMap[vue.codeData].list);

                if (vue.defaultIndex != null) {
                    vue.$nextTick(function() {
                        if (vue.$el.options.selectedIndex == -1) {
                            vue.$el.options.selectedIndex = vue.defaultIndex;
                        }
                    });
                }
            });
        }
    }
});

Vue.component('ui-checkbox', {
    props: {
        checked: {
            type: Boolean
        },
        value: {
            type: String
        },
        inline: {
            type: Boolean
        },
        readOnly2: {
            type: Boolean
        }
    },
    model: {
        prop: "checked"
    },
    template: ' \
        <div class="chkArea single" v-bind:style="{ display: inline ? \'inline\' : null }"> \
            <label class="chkBox chk vue" v-bind:class="{ on: value }" v-bind:for="checkboxId"> \
                <input type="checkbox" v-bind:id="checkboxId" v-model="modelValue" v-bind:value="value" v-bind:disabled="readOnly2 === true"></input> \
                <span v-bind:style="{ fontWeight: readOnly2 === true && modelValue === true ? \'bold\' : \'\' }"><slot></slot></span> \
            </label> \
        </div> \
    ',
    data: function() {
        return {
            checkboxId: "checkbox" + Vue.newComponentIndex()
        };
    },
    computed: {
        modelValue: {
            get: function() {
                return this.checked;
            },
            set: function(checked) {
                this.$emit("input", checked);
            }
        }
    },
    watch: {
        value: function() {
            this.$emit("input", this.value);
        }
    }
});

Vue.component('ui-checkbox-head', {
    props: {
        data: {
            type: Array
        }
    },
    template: ' \
        <div class="chkArea single"> \
            <label class="chkBox chk vue" v-bind:for="checkboxId" v-bind:class="{ on: listHeadChecked }"> \
                <input type="checkbox" v-bind:id="checkboxId" v-bind:checked="listHeadChecked" v-on:change="checkAllRow();"></input> \
                <span><slot></slot></span> \
            </label> \
        </div> \
    ',
    computed: {
        checkboxId: function() {
            if (Vue.listHeadCheckIndex == null) {
                Vue.listHeadCheckIndex = 0;
            }

            return "listHeadCheck" + (++Vue.listHeadCheckIndex);
        },
        listHeadChecked: function() {
            if (this.data == null) {
                return false;
            }

            this.data.forEach(function(rowData) {
                if (rowData._rowCheck_ == null) {
                    Vue.set(rowData, "_rowCheck_", false);
                }

                if (rowData._JOB_TYPE == null) {
                    Vue.set(rowData, "_JOB_TYPE", "U");
                }
            });

            var list = this.data.filter(function(rowData) {
                return rowData._JOB_TYPE != "D";
            });

            if (list.length == 0) {
                return false;
            }

            for (var i = 0; i < list.length; i++) {
                if (list[i]._rowCheck_ != true) {
                    return false;
                }
            }

            return true;
        }
    },
    methods: {
        checkAllRow: function() {
            var headChecked = $('#' + this.checkboxId).prop("checked");

            for (var i = 0; i < this.data.length; i++) {
                this.data[i]._rowCheck_ = headChecked;
            }

            this.data.splice(0, 0);
        }
    }
});

Vue.component('ui-checkbox-row', {
    props: {
        data: {
            type: Object
        }
    },
    template: ' \
        <div class="chkArea single"> \
            <label class="chkBox chk vue" v-bind:for="checkboxId" v-bind:class="{ on: data._rowCheck_ }"> \
                <input type="checkbox" v-bind:id="checkboxId" v-model="data._rowCheck_"></input> \
                <span><slot></slot></span> \
            </label> \
        </div> \
    ',
    computed: {
        checkboxId: function() {
            if (Vue.listRowCheckIndex == null) {
                Vue.listRowCheckIndex = 0;
            }

            return "listRowCheck" + (++Vue.listRowCheckIndex);
        }
    }
});

Vue.component('ui-ckeditor4', {
    props: {
        value: {
            default: ""
        },
        height: {
            type: String,
            default: "300px"
        },
        readOnly: {
            type: Boolean
        }
    },
    template: ' \
        <div> \
            <textarea style="display: none;">{{ value }}</textarea> \
            <textarea style="display: none;"></textarea> \
        </div> \
    ',
    data: function() {
        return {
            editor: null,
            onChangeEmitted: null
        };
    },
    updated: function() {
        this.replaceEditor();
    },
    methods: {
        replaceEditor: function() {
            var vue = this;

            if (vue.onChangeEmitted === true) {
                return;
            }

            if (vue.editor != null && CKEDITOR.instances[vue.editor.name] != null) {
                CKEDITOR.instances[vue.editor.name].destroy();
            }

            var csrfParameterName = $('meta[name="X-CSRF-Parameter-Name"]').attr("content");
            var csrfToken = $('meta[name="X-CSRF-Token"]').attr("content");
            var $textarea = $(vue.$el).children('textarea').eq(1).val(vue.value);

            vue.editor = CKEDITOR.replace($textarea[0], {
                language: "ko",
                extraPlugins: "image2",
                allowedContent: true,
                filebrowserUploadUrl: CommonUtil.contextPath + "/common/file/uploadImage?" + csrfParameterName + "=" + csrfToken,
                removePlugins: "elementspath",
                font_names: (function() {
                    var fontNames = [];

                    fontNames.push("굴림/Gulim");
                    fontNames.push("돋움/Dotum");
                    fontNames.push("바탕/Batang");
                    fontNames.push("궁서/Gungsuh");
                    fontNames.push("맑은 고딕/Malgun");
                    fontNames.push("Arial/arial");
                    fontNames.push("Comic Sans MS/comic");
                    fontNames.push("Courier New/cour");
                    fontNames.push("Georgia/georgia");
                    fontNames.push("Lucida Sans/LSANS");
                    fontNames.push("Tahoma/tahoma");
                    fontNames.push("Times New Roman/times");
                    fontNames.push("Trebuchet MS/trebuc");
                    fontNames.push("Verdana/verdana");

                    return fontNames.join(";");
                })(),
                readOnly: vue.readOnly,
                resize_enabled: false,
                height: vue.height
            });

            vue.editor.on("instanceReady", function() {
                this.document.on("keyup", vue.updateEditorData);
            });

            vue.editor.on("change", vue.updateEditorData);
        },
        updateEditorData: function() {
            this.onChangeEmitted = true;

            this.$emit("input", this.editor.getData());

            this.$nextTick(function() {
            });
        }
    }
});

Vue.component('ui-filepond', {
    props: {
        data: {
            type: Array,
            default: function() {
                return [];
            }
        },
        multiple: {
            type: Boolean
        },
        policy: {
            type: String,
            default: "default"
        }
    },
    model: {
        prop: "data"
    },
    template: ' \
        <div> \
            <div v-bind:data-file-list="data"></div> \
            <input type="file" \
                class="filepond" \
                name="filepond" \
                data-allow-reorder="true" \
                v-bind:multiple="multiple" \
            ></input> \
        </div> \
    ',
    data: function() {
        return {
            pond: null,
            fileList: null,
            fileListForDelete: null,
            processFilesCallback: null
        };
    },
    mounted: function() {
        if (this.$root.FilePondVues == null) {
            this.$root.FilePondVues = [];
        }

        this.$root.FilePondVues.push(this);

        FilePond.setOptions({
            allowDrop: true,
            allowReplace: false,
            instantUpload: false
        });
    },
    updated: function() {
        if (this.fileList === this.data) {
            return;
        }

        this.fileList = this.data;

        if (this.pond != null) {
            this.pond.destroy();
            this.pond = null;
        }

        FilePond.setOptions({
            server: this.getServerOptions()
        });

        var vue = this;

        window.setTimeout(function() {
            vue.pond = FilePond.create($(vue.$el).children('input:file')[0], {
                labelIdle: "이곳에 파일을 드래그하거나 클릭하세요.",
                labelFileProcessingError: function(error) {
                    if (error.body != null && error.body.message != null) {
                        return error.body.message;
                    }

                    return "파일 업로드 에러가 발생했습니다.";
                },
                files: (function() {
                    var files = [];

                    for (var i = 0; i < vue.data.length; i++) {
                        var file = vue.data[i];

                        files.push({
                            options: {
                                type: "local",
                                file: {
                                    fileId: file.fileId,
                                    name: file.orginlFileNm,
                                    size: file.fileSize
                                }
                            },
                            fileInfo: file
                        });
                    }

                    return files;
                })(),
                onerror: function(error, file, status) {
                    vue.processFilesCallback = null;

                    if (error.body != null && error.body.message != null) {
                        alert(error.body.message);
                    } else {
                        alert(status.main);
                    }
                },
                onaddfile: function(error, file) {
                    vue.resetFileList();
                },
                onprocessfiles: function() {
                    vue.resetFileList();

                    if (vue.processFilesCallback != null) {
                        vue.processFilesCallback(vue.$root);
                        vue.processFilesCallback = null;
                    }
                },
                onreorderfiles: function(files) {
                    vue.resetFileList();
                },
                onremovefile: function(error, file) {
                    if (file.serverId != null) {
                        if (vue.fileListForDelete == null) {
                            vue.fileListForDelete = [];
                        }

                        var file = file.serverId.fileInfo;

                        if ($.inArray(file, vue.fileListForDelete) == -1) {
                            vue.fileListForDelete.push(file);
                        }
                    }

                    vue.resetFileList();
                }
            });
        });
    },
    methods: {
        getServerOptions: function() {
            var server = { url: CommonUtil.contextPath + "/common/file" };

            server.process = { headers: {} };
            server.process.url = "/upload" + (this.policy == null ? "" : "?policy=" + this.policy);

            server.process.onerror = function(error) {
                if ($.type(error) === "string") {
                    return JSON.parse(error);
                }

                return error;
            };

            server.revert = { headers: {} };
            server.revert.url = "/revert";
            server.revert.headers["Content-Type"] = "application/json";
            server.revert.headers["X-HTTP-Method-Override"] = "DELETE";

            var csrfHeaderName = $('meta[name="X-CSRF-Header-Name"]').attr("content");
            var csrfToken = $('meta[name="X-CSRF-Token"]').attr("content");

            if (csrfHeaderName != null && csrfHeaderName != "") {
                server.process.headers[csrfHeaderName] = csrfToken;
                server.revert.headers[csrfHeaderName] = csrfToken;
            }

            return server;
        },
        upload: function(callback) {
            if (this.isAllFilesUploaded() !== true) {
                this.processFilesCallback = callback;
                this.pond.processFiles();
            } else if (callback != null) {
                callback(this.$root);
            }
        },
        resetFileList: function() {
            this.data.length = 0;

            if (this.fileListForDelete != null) {
                for (var i = 0; i < this.fileListForDelete.length; i++) {
                    var file = this.fileListForDelete[i];

                    if (file != null) {
                        file._JOB_TYPE = "D";

                        this.data.push(file);
                    }
                }
            }

            var files = this.pond.getFiles();

            for (var i = 0; i < files.length; i++) {
                var file = getFile(files[i].serverId);

                if (file == null) {
                    this.data.push(files[i].file);
                } else {
                    file.sortOrdr = i + 1;

                    this.data.push(file);
                }
            }

            function getFile(serverId) {
                if ($.type(serverId) === "string") {
                    return JSON.parse(serverId);
                }

                if ($.type(serverId) === "object" && serverId.fileInfo != null) {
                    return serverId.fileInfo;
                }
            }
        },
        isAllFilesUploaded: function() {
            var files = this.pond.getFiles();

            for (var i = 0; i < files.length; i++) {
                if (files[i].serverId == null) {
                    return false;
                }
            }

            return true;
        }
    }
});

Vue.component('ui-filedownload', {
    props: {
        data: {
            type: Array,
            default: function() {
                return [];
            }
        }
    },
    model: {
        prop: "data"
    },
    template: ' \
        <div> \
            <div v-for="file in data" v-bind:key="file.fileId"> \
                <img src="' + CommonUtil.contextPath + '/shared/common/ui/images/icon_file.png" style="vertical-align: middle;"> \
                <a v-bind:href="getDownloadUrl(file)"> \
                    <span>{{ file.orginlFileNm }} ({{ getFileSize(file) }})</span> \
                </a> \
            </div> \
        </div> \
    ',
    methods: {
        getDownloadUrl: function(file) {
            return CommonUtil.contextPath + "/common/file/download?dwldId=" + file.dwldId;
        },
        getFileSize: function(file) {
            return CommonUtil.formatter.number(file.fileSize) + " Byte";
        }
    }
});

Vue.component('ui-jstree', {
    props: {
        data: {
            type: Array,
            default: function() {
                return [];
            }
        },
        value: {
            type: String
        },
        columns: {
            type: Object
        },
        options: {
            type: Object,
            default: function() {
                return {
                    initiallyOpenLevel: 0,
                    nodeText: null
                };
            }
        },
        showCheckbox: {
            type: Boolean
        },
        multiple: {
            type: Boolean
        },
        allowBatch: {
            type: Boolean
        },
        wholeRow: {
            type: Boolean
        },
        draggable: {
            type: Boolean
        }
    },
    model: {
        prop: "value"
    },
    template: ' \
        <v-jstree v-bind:data="treeData" \
            v-bind:show-checkbox="showCheckbox" \
            v-bind:multiple="multiple" \
            v-bind:allow-batch="allowBatch" \
            v-bind:whole-row="wholeRow" \
            v-bind:draggable="draggable" \
            v-on:item-click="itemClick" \
        ></v-jstree> \
    ',
    data: function() {
        return {
            idDataMap: null,
            savedTreeItems: null,
            selectedData: null
        };
    },
    computed: {
        treeData: function() {
            if (this.columns == null) {
                return this.data;
            }

            this.idDataMap = {};

            var treeOptions = this.savedTreeItems;

            if (treeOptions == null) {
                treeOptions = {};

                for (var i = 0; i < this.data.length; i++) {
                    this.idDataMap[this.data[i][this.columns.id]] = this.data[i];

                    var treeItem = this.data[i]._treeItem_;

                    if (treeItem == null) {
                        continue;
                    }

                    if (this.columns.selected != null) {
                        this.data[i][this.columns.selected] = treeItem.selected;
                    }

                    treeOptions[treeItem.id] = {
                            opened: treeItem.opened,
                            selected: treeItem.selected,
                            disabled: treeItem.disabled
                    };
                }
            }

            var treeData = [];

            for (var i = 0; i < this.data.length; i++) {
                var node = {};

                this.updateItemData(this.data[i], node);

                if (this.columns.selected == null) {
                    node.selected = (node.id == this.value);
                } else {
                    node.selected = (this.data[i][this.columns.selected] === true);
                }

                var parentNode;

                if (node.parent != null) {
                    parentNode = this.getNodeById(this.data[i][this.columns.parent]);
                } else if (treeData.length > 0 && treeData[0].id == null) {
                    parentNode = treeData[0];
                } else {
                    parentNode = null;
                }

                this.data[i]._treeItem_ = node;

                if (parentNode == null) {
                    node._treeLevel_ = 1;
                    treeData.push(node);
                } else {
                    node._treeLevel_ = parentNode._treeLevel_ + 1;

                    var option = treeOptions[node.id];

                    if (option == null) {
                        node.opened = (this.options.initiallyOpenLevel == -1 || node._treeLevel_ <= this.options.initiallyOpenLevel);
                    } else {
                        node.opened = option.opened;
                    }

                    if (parentNode.opened == null) {
                        var parentOption = treeOptions[parentNode.id];

                        if (parentOption == null) {
                            parentNode.opened = (this.options.initiallyOpenLevel == -1 || parentNode._treeLevel_ <= this.options.initiallyOpenLevel);
                        } else {
                            parentNode.opened = parentOption.opened;
                        }
                    }

                    if (parentNode.children == null) {
                        parentNode.children = [];
                    }

                    parentNode.children.push(node);
                }
            }

            this.savedTreeItems = null;

            setCurrentNodeParentsOpened.call(this, this.value);

            if (this.options.nodeText != null) {
                for (var i = 0; i < treeData.length; i++) {
                    resetNodeText.call(this, treeData[i]);
                }
            }

            return treeData;

            function setCurrentNodeParentsOpened(id) {
                if (id == null) {
                    return;
                }

                var node = this.getNodeById(id);

                if (node == null) {
                    return;
                }

                var parentNode = this.getNodeById(node.parent);

                if (parentNode != null) {
                    parentNode.opened = true;
                    setCurrentNodeParentsOpened.call(this, parentNode.id);
                }
            }

            function resetNodeText(node) {
                var text = this.options.nodeText(node, this.getDataById(node.id));

                if (text != null && text != "") {
                    node.text = text;
                }

                if (node.children != null) {
                    for (var i = 0; i < node.children.length; i++) {
                        resetNodeText.call(this, node.children[i]);
                    }
                }
            }
        }
    },
    methods: {
        itemClick: function(node, item, event) {
            var data;

            for (var i = 0; i < this.data.length; i++) {
                if (this.data[i]._treeItem_ !== item) {
                    continue;
                }

                data = {};

                for (var key in this.data[i]) {
                    if (key != "_treeItem_") {
                        data[key] = this.data[i][key];
                    }
                }

                if (this.columns.selected != null) {
                    this.data[i][this.columns.selected] = item.selected;
                }

                break;
            }

            this.selectedData = data;

            this.$emit("item-click", node, item, data, event);
            this.$emit("input", item.id);
        },
        getSelectedData: function() {
            return this.selectedData;
        },
        getDataById: function(id) {
            if (this.idDataMap[id] != null) {
                return this.idDataMap[id];
            }

            for (var i = 0; i < this.data.length; i++) {
                if (this.data[i]._treeItem_ != null && this.data[i]._treeItem_.id == id) {
                    return this.data[i];
                }
            }
        },
        getNodeById: function(id) {
            var itemData = this.getDataById(id);

            if (itemData != null) {
                return itemData._treeItem_;
            }
        },
        updateItemData: function(data, itemData) {
            if (itemData == null) {
                itemData = this.getDataById(data[this.columns.id]);
            }

            if (itemData == null) {
                return;
            }

            var treeItem = itemData._treeItem_;

            if (itemData._treeItem_ == null) {
                treeItem = itemData;
            } else {
                for (var key in data) {
                    itemData[key] = data[key];
                }
            }

            treeItem.id = data[this.columns.id];
            treeItem.parent = data[this.columns.parent];
            treeItem.text = data[this.columns.text];
            treeItem.icon = data[this.columns.icon];
        },
        moveNode: function(direction) {
            if (this.value == null || this.columns.sort == null) {
                return;
            }

            var itemData = this.getDataById(this.value);

            if (itemData == null) {
                return;
            }

            var treeItem = itemData._treeItem_;
            var parentNode = this.getNodeById(treeItem.parent);

            if (parentNode == null) {
                return;
            }

            var children = parentNode.children;
            var result = [];

            for (var i = 0; i < children.length; i++) {
                var childItem = this.getDataById(children[i].id);
                childItem[this.columns.sort] = i + 1;
                result.push(childItem);
            }

            for (var i = 0; i < result.length; i++) {
                if (result[i]._treeItem_ != treeItem) {
                    continue;
                }

                if (i + direction < 0 || i + direction >= result.length) {
                    return;
                }

                result[i + direction][this.columns.sort] = result[i + direction][this.columns.sort] + (direction * (-1));
                result[i][this.columns.sort] = result[i][this.columns.sort] + direction;
            }

            return result;
        },
        saveStatus: function() {
            this.savedTreeItems = {};

            for (var i = 0; i < this.data.length; i++) {
                var treeItem = this.data[i]._treeItem_;

                if (treeItem != null) {
                    this.savedTreeItems[treeItem.id] = JSON.parse(JSON.stringify(treeItem));
                }
            }
        }
    }
});

Vue.component('ui-valid-checker', {
    props: {
        value: {
        },
        check: {
            type: String
        },
        groups: {
            type: String
        }
    },
    template: ' \
        <div class="valid-message" v-if="isVisible" v-bind:title="message">{{ message }}</div> \
    ',
    data: function() {
        return {
            tableHeader: null,
            checked: false,
            message: null
        };
    },
    computed: {
        isVisible: function() {
            return this.checked === true && this.checkValid() !== true;
        }
    },
    mounted: function() {
        var $$el = $(this.$el);

        if ($$el.closest('table').hasClass("form")) {
            this.tableHeader = $$el.closest('td').prev('th')[0];
        } else if ($$el.closest('table').hasClass("list")) {
            this.tableHeader = $$el.closest('table').children('thead').children('tr:last').children('th').eq($$el.closest('td').index())[0];
        }

        ValidationUtil.addValid(this);
    },
    beforeDestroy: function() {
        ValidationUtil.removeValid(this);
    },
    methods: {
        initValid: function() {
            this.checked = false;
        },
        checkValid: function() {
            this.checked = true;

            if (this.check == null) {
                return true;
            }

            var checks = this.check.split(",");
            var message;

            for (var i = 0; i < checks.length; i++) {
                var validator = ValidationUtil.validator[$.trim(checks[i])];

                if (validator == null) {
                    validator = ValidationUtil.validator._unknown;
                }

                message = validator(this.value);

                if (message != null) {
                    break;
                }
            }

            this.message = message;

            return message == null;
        }
    }
});

Vue.component('ui-valid-checker-duplicate', {
    props: {
        data: {
            type: Array
        },
        rowData: {
            type: Object
        },
        columnName: {
            type: String
        },
        groups: {
            type: String
        }
    },
    template: ' \
        <div class="valid-message" v-if="isVisible" v-bind:title="message">{{ message }}</div> \
    ',
    data: function() {
        return {
            checked: false,
            message: null
        };
    },
    computed: {
        isVisible: function() {
            return this.checked === true && this.checkValid() !== true;
        }
    },
    mounted: function() {
        ValidationUtil.addValid(this);
    },
    beforeDestroy: function() {
        ValidationUtil.removeValid(this);
    },
    methods: {
        initValid: function() {
            this.checked = false;
        },
        checkValid: function() {
            this.checked = true;

            if (this.data == null || this.data.length == 0) {
                return true;
            }

            var valueMap = {};
            var message;

            for (var i = 0; i < this.data.length; i++) {
                if (this.data[i]._JOB_TYPE == "D") {
                    continue;
                }

                var value = this.data[i][this.columnName];

                if (value == null || value == "") {
                    continue;
                }

                if (valueMap[value] != true) {
                    valueMap[value] = true;
                } else if (this.data[i] === this.rowData) {
                    message = "중복된 값입니다.";
                }

                if (this.data[i] === this.rowData) {
                    break;
                }
            }

            this.message = message;

            return message == null;
        }
    }
});

Vue.component('ui-grid-paging', {
    props: {
        request: {
            type: Object
        },
        result: {
            type: Object
        },
        readList: {
            type: Function
        },
        buttons: {
            type: Array
        }
    },
    template: ' \
        <div> \
            <div class="bbs_top"> \
                <div class="bbs_total"> \
                    <span class="total_num" v-html="titleMessage"></span> \
                </div> \
                <div class="bbs_top_info"> \
                    <ul> \
                        <li> \
                            <button v-for="button in buttons" v-bind:class="button.className" \
                                style="margin-left: 4px;" \
                                v-on:click="button.onClick" \
                            >{{ button.label }}</button> \
                            <button name="excelDownloadButton" class="btn_excel" \
                                v-bind:disabled="result.list == null || result.list.length == 0" \
                                v-on:click="downloadExcel($event);" \
                            >엑셀다운로드</button> \
                        </li> \
                        <li v-if="request.paging != null"> \
                            <select name="recordCountPerPage" style="height: 28px;" \
                                v-model="resultPaging.recordCountPerPage" \
                            > \
                                <option \
                                    v-for="recordCountPerPage in recordCountPerPageList" \
                                    v-bind:value="recordCountPerPage" \
                                >{{ recordCountPerPage }}개씩보기</option> \
                            </select> \
                        </li> \
                    </ul> \
                </div> \
            </div> \
            <slot></slot> \
            <div id="pageControlArea" v-if="resultPaging.currentPageNo != null"> \
                <button class="pageControl pagePrev2" \
                    v-bind:disabled="resultPaging.currentPageNo == 1" \
                    v-on:click="changePageNo($event, 1);" \
                >&lt;&lt;</button> \
                <button class="pageControl pagePrev1" \
                    v-bind:disabled="resultPaging.currentPageNo == 1" \
                    v-on:click="changePageNo($event, Math.max(resultPaging.currentPageNo - 1, 1));" \
                >&lt;</button> \
                <span v-for="(pageNo, index) in pageList"> \
                <span v-if="index > 0">&nbsp;</span><button class="pageControl pageNum" \
                    v-bind:class="{ on: pageNo == resultPaging.currentPageNo }" \
                    v-on:click="changePageNo($event, pageNo);" \
                >{{ pageNo }}</button> \
                </span> \
                <button class="pageControl pageNext1" \
                    v-bind:disabled="resultPaging.currentPageNo == resultPaging.totalPageCount" \
                    v-on:click="changePageNo($event, Math.min(resultPaging.currentPageNo + 1, resultPaging.totalPageCount));" \
                >&gt;</button> \
                <button class="pageControl pageNext2" \
                    v-bind:disabled="resultPaging.currentPageNo == resultPaging.totalPageCount" \
                    v-on:click="changePageNo($event, resultPaging.totalPageCount);" \
                >&gt;&gt;</button> \
            </div> \
        </div> \
    ',
    computed: {
        titleMessage: function() {
            var titleMessage;
            var emptyMessage;
            var $table = $(this.$el).children('table');
            var $tbody = $table.children('tbody:last');

            $tbody.children('tr[empty-message-row=""]').remove();

            if (this.result.error === true) {
                titleMessage = '(Error)';
            } else if (this.result.paging != null && this.result.paging.totalRecordCount != null) {
                titleMessage = '총 ' + CommonUtil.formatter.number(this.result.paging.totalRecordCount) + ' 건';
                emptyMessage = (this.result.paging.totalRecordCount == 0);
            } else if (this.result.list != null) {
                titleMessage = '총 ' + CommonUtil.formatter.number(this.result.list.length) + ' 건';
                emptyMessage = (this.result.list.length == 0);
            } else if (this.result.loadStarted) {
                titleMessage = 'Loading...';
            } else {
                titleMessage = '(Ready)';
            }

            if (emptyMessage === true) {
                var $thead = $table.children('thead');

                var $tr = $('<tr>').attr({ "empty-message-row": "" }).appendTo($tbody);

                $('<td>').attr({ "colspan": $thead.find('th').length })
                    .css({ "text-align": "center" })
                    .text("(검색된 결과가 존재하지 않습니다)")
                    .appendTo($tr);
            }

            return titleMessage;
        },
        recordCountPerPageList: function() {
            return GridUtil.globalProps.paging.recordCountPerPageList;
        },
        resultPaging: function() {
            if (this.result == null || this.result.paging == null) {
                return GridUtil.globalProps.paging;
            }

            return this.result.paging;
        },
        pageList: function() {
            var list = [];

            if (this.result.paging != null) {
                for (var i = this.result.paging.firstPageNoOnPageList; i <= this.result.paging.lastPageNoOnPageList; i++) {
                    list.push(i);
                }
            }

            return list;
        }
    },
    mounted: function() {
        var changeRecordCountPerPage = this.changeRecordCountPerPage;

        $(this.$el).find('[name="recordCountPerPage"]').on("change", function() {
            var newRecordCountPerPage = $(this).val();

            if (newRecordCountPerPage != null) {
                changeRecordCountPerPage(newRecordCountPerPage);
            }
        });

        this.initSortColumns();
    },
    methods: {
        initSortColumns: function() {
            var vue = this;
            var $table = $(vue.$el).children('table');
            var $ths = $table.children('thead').find('th[column-name][column-sort]');
            var sortClassNames = { ASC: "btn_sort_up", DESC: "btn_sort_down" };

            $ths.each(function() {
                var $th = $(this);
                var columnName = $th.attr("column-name");

                if (columnName == "" || $th.children('span.sort').length > 0) {
                    return;
                }

                var $span = $('<span class="sort btn_sort_basic">&nbsp;</span>').prependTo(this);
                var columnSort = $th.attr("column-sort").toUpperCase();

                if (columnSort != "") {
                    if (vue.request.paging != null) {
                        if (vue.request.paging.orderBy.columnList == null) {
                            vue.request.paging.orderBy.columnList = [];
                        }

                        vue.request.paging.orderBy.columnList.push({
                            columnName: columnName,
                            sortDirection: columnSort
                        });
                    }

                    $span.addClass(sortClassNames[columnSort]);
                }

                $th.css({ "cursor": "pointer" }).click(function() {
                    var $th = $(this);
                    var $span = $th.children('span.sort');
                    var orderByDirection;

                    $th.siblings().children('span.sort').removeClass(sortClassNames.ASC).removeClass(sortClassNames.DESC);

                    if ($span.hasClass(sortClassNames.ASC)) {
                        $span.removeClass(sortClassNames.ASC);
                        $span.addClass(sortClassNames.DESC);
                        orderByDirection = "DESC";
                    } else {
                        $span.removeClass(sortClassNames.DESC);
                        $span.addClass(sortClassNames.ASC);
                        orderByDirection = "ASC";
                    }

                    if (vue.request.paging != null) {
                        vue.request.paging.orderBy.columnList = [{
                            columnName: columnName,
                            sortDirection: orderByDirection
                        }];

                        vue.request.paging.currentPageNo = 1;
                    }

                    vue.readList();
                });
            });
        },
        changeRecordCountPerPage: function(newRecordCountPerPage) {
            this.request.paging.recordCountPerPage = newRecordCountPerPage;
            this.request.paging.currentPageNo = 1;

            this.readList();
        },
        changePageNo: function(event, newPageNo) {
            if ($(event.target).hasClass("disable")) {
                return;
            }

            this.request.paging.currentPageNo = newPageNo;

            this.readList();
        },
        downloadExcel: function(event) {
            var vue = this;

            var $button = $(vue.$el).find('[name="excelDownloadButton"]');

            if ($button.prop("disabled") === true) {
                return;
            }

            var $table = $(vue.$el).children('table');

            if ($table.length > 0) {
                var $thead = $table.children('thead');

                if ($thead.find('th[column-name]').length == 0) {
                    alert("다운로드할 컬럼이 정의되지 않았습니다.");
                    return;
                }
            }

            var $downloadPopup = $('#excelDownloadPopup');

            if ($downloadPopup.length == 0) {
                $downloadPopup = $('\
                    <div id="excelDownloadPopup"> \
                        <div class="pageContents"> \
                            <ui-radios code-data="/common.grid/excelDownloadDownloadMode" v-model="downloadScope"></ui-radios> \
                        </div> \
                        <div class="page-space tar"> \
                            <ui-button class="page" v-on:click="downloadExcel();">다운로드</ui-button> \
                            <ui-button class="list" v-on:click="closePopup();">취소</ui-button> \
                        </div> \
                    </div> \
                ').appendTo('body');

                new Vue({
                    el: '#excelDownloadPopup',
                    data: {
                        downloadScope: "1"
                    },
                    methods: {
                        downloadExcel: function() {
                            GridUtil.downloadExcel(vue, this.downloadScope);
                        },
                        closePopup: function() {
                            $(this.$el).dialog("close");
                        }
                    }
                });
            }

            $('#excelDownloadPopup').dialog({
                title: "엑셀 다운로드",
                modal: true,
                width: 300,
                height: 120
            });
        }
    }
});

Vue.component('ui-data-table', {
    props: {
        data: {
            type: Array
        }
    },
    template: ' \
        <table class="list"> \
            <colgroup v-if="!!$slots.colgroup"> \
                <slot name="colgroup"></slot> \
            </colgroup> \
            <thead> \
                <slot name="thead"></slot> \
            </thead> \
            <tbody> \
                <slot name="tbody"></slot> \
            </tbody> \
            <tfoot v-if="!!$slots.tfoot"> \
                <slot name="tfoot"></slot> \
            </tfoot> \
        </table> \
    '
});

Vue.component('ui-list-button-append', {
    props: {
        data: {
            type: Array
        },
        idColumnName: {
            type: String
        },
        sortColumnName: {
            type: String
        },
        newRowData: {
            type: Object
        },
        appendFunction: {
            type: Function
        }
    },
    template: ' \
        <button v-on:click="appendRow();"> \
            <slot>추가</slot> \
        </button> \
    ',
    methods: {
        appendRow: function() {
            var vue = this;

            if (vue.appendFunction == null) {
                vue.pushData(vue.newRowData == null ? {} : vue.newRowData);
            } else {
                vue.appendFunction(function(data) {
                    if ($.type(data) === "array") {
                        for (var i = 0; i < data.length; i++) {
                            vue.pushData(data[i]);
                        }
                    } else {
                        vue.pushData(data);
                    }
                });
            }
        },
        pushData: function(rowData) {
            if (rowData == null) {
                return;
            }

            if (rowData[this.idColumnName] == null) {
                if (Vue.idValueIndex == null) {
                    Vue.idValueIndex = 0;
                }

                rowData[this.idColumnName] = ++Vue.idValueIndex;
            } else {
                for (var i = 0; i < this.data.length; i++) {
                    if (this.data[i][this.idColumnName] == rowData[this.idColumnName]) {
                        return;
                    }
                }
            }

            rowData._JOB_TYPE = "C";
            rowData._rowCheck_ = false;

            if (this.sortColumnName != null && this.sortColumnName != "") {
                var sortNo = 0;

                for (var i = 0; i < this.data.length; i++) {
                    if (this.data[i]._JOB_TYPE != "D") {
                        this.data[i][this.sortColumnName] = ++sortNo;
                    }
                }

                rowData[this.sortColumnName] = sortNo + 1;
            }

            this.data.push(rowData);
        }
    }
});

Vue.component('ui-list-button-delete', {
    props: {
        data: {
            type: Array
        },
        sortColumnName: {
            type: String
        }
    },
    template: ' \
        <button v-on:click="deleteSelectedRow();"> \
            <slot>삭제</slot> \
        </button> \
    ',
    methods: {
        deleteSelectedRow: function() {
            for (var i = this.data.length - 1; i >= 0; i--) {
                if (this.data[i]._rowCheck_ == true) {
                    if (this.data[i]._JOB_TYPE == "C") {
                        this.data.splice(i, 1);
                    } else {
                        this.data[i]._JOB_TYPE = "D";

                        if (this.sortColumnName != null && this.sortColumnName != "") {
                            this.data[i][this.sortColumnName] = 0;
                        }
                    }
                }
            }

            var sortNo = 0;

            if (this.sortColumnName != null && this.sortColumnName != "") {
                for (var i = 0; i < this.data.length; i++) {
                    if (this.data[i]._JOB_TYPE != "D") {
                        this.data[i][this.sortColumnName] = ++sortNo;
                    }
                }
            }
        }
    }
});

Vue.component('ui-iframe', {
    props: {
        value: {
            default: ""
        }
    },
    template: ' \
        <div v-bind:data-html="value"></div> \
    ',
    data: function() {
        return {
            iframe: null
        };
    },
    updated: function() {
        this.loadHTML();
    },
    methods: {
        loadHTML: function() {
            var $div = $(this.$el).empty();
            var $ifr = $('<iframe style="width: 100%; height: 1px; border: none;">').appendTo($div);

            $ifr.on("load", this.loadHTML);

            this.iframe = $ifr[0];

            this.iframe.contentWindow.document.write(this.value);

            this.resetHeight();
        },
        resetHeight: function() {
            if (this.iframe == null) {
                return;
            }

            var vue = this;
            var doc = vue.iframe.contentWindow.document;

            $(vue.iframe).height($(doc.body).outerHeight(true));

            window.setTimeout(function() {
                vue.resetHeight();
            }, 500);
        }
    }
});
