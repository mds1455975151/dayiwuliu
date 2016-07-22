/**
 * 2012-12-26 common upload init for all pages
 */
var caseType = '';//定义全局变量 上传经典案例需要用到 （只能传一个附件）
//全局变量，记录file的id和name
var hideFileName='';
var hideFileId='';
//统一初始化一个附件上传组件
/**
 * 
 * @param targetId 初始化附件的页面id
 * @param pageType 默认为add：新增页面、update：修改页面、detail：详情页面
 * @param incId 公司id
 * @param count 可上传的附件数
 * @param type 模块名称 如 people、customer、requirement等
 * @param 显示附件列表的id，默认为没有这个参数；如果参数为空，则根据目标元素的下的attachmentListArea来确定附件列表显示的位置
 * @param 列表上传附件的名称
 */
function formatDate(mmSeconds) {
    var format = 'yyyy-MM-dd HH:mm:ss';
    var t = new Date(mmSeconds);
    var tf = function (i) { return (i < 10 ? '0' : '') + i };
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
        switch (a) {
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    })
}
/**
 * 时间格式化方法
 */
Date.prototype.format = function (format) {
    /*
	 * eg:format="yyyy-MM-dd hh:mm:ss";
	 */
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()
        // millisecond
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

function utf8_decode(str_data) {
    // Converts a UTF-8 encoded string to ISO-8859-1 
    //
    // version: 1009.2513
    // discuss at: http://phpjs.org/functions/utf8_decode
    // +   original by: Webtoolkit.info (http://www.webtoolkit.info/)
    // +      input by: Aman Gupta
    // +   improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
    // +   improved by: Norman "zEh" Fuchs
    // +   bugfixed by: hitwork
    // +   bugfixed by: Onno Marsman
    // +      input by: Brett Zamir (http://brett-zamir.me)
    // +   bugfixed by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
    // *     example 1: utf8_decode('Kevin van Zonneveld');
    // *     returns 1: 'Kevin van Zonneveld'
    var tmp_arr = [], i = 0, ac = 0, c1 = 0, c2 = 0, c3 = 0;

    str_data += '';

    while (i < str_data.length) {
        c1 = str_data.charCodeAt(i);
        if (c1 < 128) {
            tmp_arr[ac++] = String.fromCharCode(c1);
            i++;
        } else if ((c1 > 191) && (c1 < 224)) {
            c2 = str_data.charCodeAt(i + 1);
            tmp_arr[ac++] = String.fromCharCode(((c1 & 31) << 6) | (c2 & 63));
            i += 2;
        } else {
            c2 = str_data.charCodeAt(i + 1);
            c3 = str_data.charCodeAt(i + 2);
            tmp_arr[ac++] = String.fromCharCode(((c1 & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
            i += 3;
        }
    }

    return tmp_arr.join('');
}


function commonUpload1(targetId, pageType, moduleType, incId, count, type, taskId, listAreaId, toolName, fileIdsName, seat, frame, getinfourl, showErrorMethod, showErrorMsgId) {
   
}

function commonUpload(targetId, pageType, moduleType, incId, count, type, taskId, fileType, listAreaId, toolName, fileIdsName, seat, frame, getinfourl, showErrorMethod, showErrorMsgId, callbak, lead) {
    //统一初始化一个附件上传组件
    var taskId0, listAreaId0, fileIdsName0, seat0, frame0;
    taskId0 = ((typeof (taskId) == 'undefined' || taskId == null || taskId == '' || taskId == undefined) ? 'taskId' : taskId);
    listAreaId0 = ((typeof (listAreaId) == 'undefined' || listAreaId == null || listAreaId == '' || listAreaId == undefined) ? 'listArea1' : listAreaId);
    fileIdsName0 = ((typeof (fileIdsName) == 'undefined' || fileIdsName == null || fileIdsName == '' || fileIdsName == undefined) ? 'fileIds' : fileIdsName);
    seat0 = ((typeof (seat) == 'undefined' || seat == null || seat == '' || seat == undefined) ? 'before' : seat);
    frame0 = ((typeof (frame) == 'undefined' || frame == null || frame == '' || frame == undefined) ? '' : frame);
    var getinfourl0 = (typeof (getinfourl) != 'undefined' && getinfourl != '' ? getinfourl : undefined);
    var mu0 = ((typeof (frame) == 'undefined' || frame == null || frame == '' || frame == undefined) ? 'mu' : toolName);
    var ext, size;//附件限制设置
   
    $.ajax({
        url: '/inc/' + incId + '/attachment/' + moduleType + '/list.json',
        dataType: 'json',
        type: 'GET',
        cache: false,
        async: false,
        success: function (data) {
            var attachment = data.attachmentSetting;
            ext = attachment.fromat;
            size = attachment.size;
        }
    });
    ext = (typeof (ext) == 'undefined' ? '' : ext.replace(/\*\./g, ""));
    if (ext.charAt(0) == ';') {
        ext = ext.substring(1);
    }
    if (ext.charAt(ext.length - 1) == ';') {
        ext = ext.substring(0, ext.length - 1);
    }
    ext = ext.replace(/;/g, ",");

    caseType = type;
    var fd = $('#' + targetId);//附件输出区域
    var fdInnerHtml = [];
    fd.empty();//清空区域
    var fi = $('input[name=' + fileIdsName0 + ']');//获取id输出框
    if (fi.size() == 0) {//如果没有id输出框就创建一个
        //		fdInnerHtml.push('<input type="text" value="" name="');
        //		fdInnerHtml.push(fileIdsName0);
        //		fdInnerHtml.push('" id="');
        //		fdInnerHtml.push(targetId);
        //		fdInnerHtml.push('_fileIds" style="display:none;"/>');
        fi = $('<input type="text" value="" name="' + fileIdsName0 + '" id="' + targetId + '_fileIds" style="display:none;"/>').appendTo(fd);
    }

    //输出已有附件的列表
    try {

        //判断是否是导入导出页面上传
        if (lead !== 'lead') {
            if (seat0 == 'before') {
                fdInnerHtml.push(frame0);
            }
            fdInnerHtml.push('<button id="');
            fdInnerHtml.push(targetId);
            if(pageType=='add'){
            	if(fileType == 1) {
            		fdInnerHtml.push('_addImagesButton"  class="btn  btn-sm byellow">上传</button>&nbsp;&nbsp;<em>图片大小不超过3M，限上传1张，支持JPG、JPEG、PNG格式</em>');
            	} else if (fileType == 2) {
            		fdInnerHtml.push('_addVediosButton" class="divAddFiles" href="javascript:">添加视频</a></span>&nbsp;</div>');
            	} else {
            		fdInnerHtml.push('_addFilesButton" class="divAddFiles" href="javascript:">添加附件</a></span>&nbsp;</div>');
            	}
            	
            }else if(pageType=='edit'){
            	if(fileType == 1) {
            		fdInnerHtml.push('_addImagesButton" class="divAddFiles" href="javascript:">修改封面</a></span>&nbsp;</div>');
            	} else if (fileType == 2) {
            		fdInnerHtml.push('_addVideosButton" class="divAddFiles" href="javascript:">修改视频</a></span>&nbsp;</div>');
            	} else {
            		fdInnerHtml.push('_addFilesButton" class="divAddFiles" href="javascript:">修改附件</a></span>&nbsp;</div>');
            	}
            }
            if (seat0 == 'after') {
                fdInnerHtml.push(frame0);
            }
            fd.append(fdInnerHtml.join(''));
        } else {
            fdInnerHtml.push('<div class="orangeWrap clearfix"><div class="attachmentButtonArea fj1" id="leadDiv"><span class="fj-span"><a id="');
            fdInnerHtml.push(targetId);
            fdInnerHtml.push('_addFilesButton" class="leadSty" href="javascript:">+添加附件</a></span></div>');
            fdInnerHtml.push('&nbsp;<span class="proIfo">一次最多上传<span>100</span>个csv文件 ,超出100自动移除末尾文件</span></div>');
            fd.append(fdInnerHtml.join(''));
        }

        if (typeof (plupload) == "undefined") {
            $.getScript(scriptsRoot + '/plupload/plupload.full.min.js', function () {
                var btn = targetId;
                if (fileType==1){
                	btn += "_addImagesButton";
                } else if (fileType == 2) {
                	btn += "_addVediosButton";
                } else {
                	btn += "_addFilesButton";
                }
                $.getScript(scriptsRoot + '/plupload/i18n/zh_CN.js');
                if (lead !== 'lead') {
                    pluploadInit(targetId, btn, count, ext, size, listAreaId0, taskId0, fi, null, fileType);
                } else {
                    pluploadInit(targetId, btn, count, ext, size, listAreaId0, taskId0, fi, lead, fileType);
                }
                if (typeof (initSuccessUploadFile) == "function" && targetId != 'letterAttListArea') {
                    initSuccessUploadFile(listAreaId0, taskId0);
                }
            });
        } else {
        	var btn = targetId;
        	if (fileType==1){
            	btn += "_addImagesButton";
            } else if (fileType == 2) {
            	btn += "_addVediosButton";
            } else {
            	btn += "_addFilesButton";
            }
            pluploadInit(targetId, btn, count, ext, size, listAreaId0, taskId0, fi, null, fileType);

//            if (typeof (initSuccessUploadFile) == "function" && targetId != 'letterAttListArea') {
//                initSuccessUploadFile(listAreaId0, taskId0);
//            }
        }
    } catch (err) {
        txt = "此页面存在一个错误。\n\n";
        txt += "错误描述: " + err.description + "\n\n";
        txt += "点击OK继续。\n\n";
        alert(txt);
    }
}
//根据文件后缀判断显示图片
function checkShowImg(fileName) {
    var suffixArray = fileName.split('.')
    var suffix = suffixArray[suffixArray.length - 1];
    var imgName = '';
    if (suffix == 'txt' || suffix == 'bmp' || suffix == 'doc' || suffix == 'docx' || suffix == 'gif' || suffix == 'jpg' || suffix == 'pdf' || suffix == 'ppt' || suffix == 'pptx' || suffix == 'rar' || suffix == 'xls' || suffix == 'xlsx' || suffix == 'png' || suffix == 'zip') { // 详细文件类型图片没有收集齐
        imgName = suffix + '.gif';
    } else {
        imgName = 'doc.gif';
    }
    return imgName;
}

//定义附件列表
//file对象的属性：filestatus、id、index、name、post、size、type
function showLead(file, showFatherDivId) {
    //删除提示信息
    $('#' + showFatherDivId).parent().find('.errorStyle').remove();
    $('<div class="peopleInB_list" id="' + file.id + '_fatherDiv"><div class="peopleInB_Title peopleInB_CSV" id="' + file.id + '">' + file.name + '</div><div id="progressbar01"></div><span class="peopleInB_Success"><div id="' + file.id + '_div" class="waitUpload">待上传</div></span><div class="thisPro"></div></div>').appendTo($('#' + showFatherDivId));

    Utils.resetPopupWrap();
}

function showDiv(file, showFatherDivId) {
    //删除提示信息

    $('#' + showFatherDivId).parent().find('.errorStyle').remove();
    $('<ul class="attContent clearfix" id="' + file.id + '_fatherDiv">'
			+ '<li class="attName textoverflow">' + file.name + '</li>'
			+ '<li class="attTime">' + new Date().format("yyyy-MM-dd hh:mm:ss") + '</li>'
			+ '<li class="attOperate textoverflow"><div id="' + file.id + '_div" class="waitUpload">待上传</div></li>'
			+ '</ul>').appendTo($('#' + showFatherDivId));
    Utils.resetPopupWrap();
}
//移除附件，移除已经上传但没有保存的附件
function removeFile(fileId, fileName, id, fileIdsDiv) {
    var _s = uploaders_cache[fileIdsDiv];
    $.each(_s.files, function (i, o) {
        if (o.name == fileName) {
            _s.removeFile(o);
            return false;
        }// 防止同名文件重复添加
    });
    $('#' + fileId + '_fatherDiv').remove();
    Utils.resetPopupWrap();
    $.ajax({
        url: dataPath + '/attachment/delete.json',
        data: 'fileId=' + id,
        dataType: 'json',
        cache: false,
        async: false,
        type: 'POST',
        success: function (data) {
            var fileIds = $('input[name=fileIds]').val();
            if (typeof (fileIds) != 'undefined') {
                fileIds = fileIds.replace(id + ',', '');
                $('input[name=fileIds]').val(fileIds);
            }
        },
        error: function () {
            $.alerts._showMessage('系统忙，请稍后重试！', '', '', '', 2000);
        }
    });
}

//选中附件复选框
function choseAllFile(obj) {
    $(':checkbox[name="fileName"]').attr("checked", obj.checked);
}
//下载附件
function download(ids, objName) {
    if (ids == undefined || ids == "") {
        ids = "";
        $(':checkbox[name="fileName"][checked]').each(function (i) {
            ids += this.value + ",";
        });
    }
    if (objName == undefined || objName == null) {
        objName = "";
    }
    if (ids.length > 0) {
        var request_url = contextPath + '/attachment/downloadFileSize.json';
        $.ajax({
            url: request_url,
            type: 'get',
            async: false,
            data: "fileIds=" + ids + "&objName=" + objName,
            success: function (result) {
                var total = result.total;
                if (total > 1024 * 1024 * 1024) {
                    jConfirm('文件超过1G,下载时间稍长,是否继续？', '提示', function (re) {
                        if (re) {
                            //window.open(contextPath + '/attachment/download?fileIds=' + ids + "&objName=" + objName);
                            window.open(nginxRoot + '/web/download?fileIds=' + ids + "&objName=" + objName);
                        }
                    });
                } else {
                    //window.open(contextPath + '/attachment/download?fileIds=' + ids + "&objName=" + objName);
                    window.open(nginxRoot + '/web/download?fileIds=' + ids + "&objName=" + objName);
                }
            },
            error: function (result) {
                $.alerts._showMessage('系统忙请稍候再试！', '', '', '', 2000);
            }
        });

    } else {
        $.alerts._showMessage("请先选择要下载的文件!", "", '', '', 2000);
    }

}
function downloadFile(fileId) {
    fileId = fileId.replace(/^\s+|\s+$/g, '');
    if (fileId != undefined && fileId.length > 0) {
        var request_url = contextPath + '/attachment/downloadFileSize.json';
        $.ajax({
            url: request_url,
            type: 'get',
            async: false,
            data: "fileIds=" + fileId,
            success: function (result) {
                var total = result.total;
                if (total > 1024 * 1024 * 1024) {
                    jConfirm('文件超过1G,下载时间稍长,是否继续？', '提示', function (re) {
                        if (re) {
                            //window.open(contextPath + '/attachment/download?fileIds=' + ids);
                            window.open(nginxRoot + '/knowledge/web/download?fileIds=' + fileId);
                        }
                    });
                } else {
                    //window.open(contextPath + '/attachment/download?fileIds=' + fileId);
                    window.open(nginxRoot + '/knowledge/web/download?fileIds=' + fileId);
                }
            },
            error: function (result) {
                $.alerts._showMessage('系统忙请稍候再试！', '', '', '', 2000);
            }
        });

    } else {
        $.alerts._showMessage('请先选择要下载的文件！', '', '', '', 2000);
    }
}

//修改时需要显示已经上传成功的附加列表
function initSuccessUploadFile(showFatherId, taskId) {
    var fid = $('#' + showFatherId).parent().attr('id');
    $.ajax({
        url: '/attachment/getFiles.json',
        data: 'taskId=' + $('#' + taskId + '').val(),
        dataType: 'json',
        type: 'GET',
        cache: false,
        async: false,
        success: function (data) {
        	if(data.result=='success'){
        		var fileList = data.attList;
        		$.each(fileList, function (i, o) {
        			$('<ul class="attContent clearfix" id="' + o.id + '_fatherDiv">'
        					+ '<li class="attName textoverflow js_name">' + o.fileTitle + '</li>'
        					+ '<li class="attTime" id="createtime">' + o.ctime.substr(0,19) + '</li>'
        					+ '<li class="attOperate textoverflow"><div id="' + o.id + '_div" class="waitUpload"><a href="javascript:removeFile(\''
        					+ o.id + '\',\'' + o.fileTitle + '\',\'' + o.id + '\',\'' + fid + '\')">删除</a></li>'
        					+ '</ul>').appendTo($('#' + showFatherId));
        		});
        	}
        }
    });
}

//显示详情页附件列表
function getFileList() {
    $.ajax({
        url: '/attachment/getFiles.json',
        data: 'taskId=' + $('#taskId').val(),
        dataType: 'json',
        type: 'GET',
        cache: false,
        async: false,
        success: function (data) {
            var fileList = data.fileList;
            //alert(fileList.length )
            if (fileList.length > 0) {
                $.each(fileList, function (i, o) {
                    var imgName = checkShowImg(o.fileTitle);
                    var size = parseInt(parseInt(o.fileSize) / 1024);

                    var fileStr = '<div class="clearfix fileDivStyle"><div class="fileCheck"><input type="checkbox" value="' + o.id + '"  name="fileName" class="attCheckbox attL1" /></div><dl style="cursor:pointer;" class="clearfix" id="' + o.id + '_fatherDiv" onclick = "downloadFile(\'' + o.id + '\')">'
                            + '<dt><span><img src="'
                            + imagesPath
                            + '/'
                            + imgName
                            + '" /></span></dt><dd class="fileNameStyle" title ="' + o.fileTitle + '">' + (Utils.getCharLen2(o.fileTitle) > 10 ? Utils.getSubstring3(o.fileTitle, 12) : o.fileTitle) + '</dd><dd class="sizeTime">' + size + 'KB' + '&nbsp;' + o.ctime.substring(0, 10) + '</dd></dl></div>';
                    $('#fileListDiv').append(fileStr);
                });
                $('.downLoadFile').show();
            } else {
                var fileStr = '<ul class="clearfix fl tl pt12"><li class="attName textoverflow fl">暂无相关资料</li></ul>';
                $('#fileListDiv').append(fileStr);
                $('.downLoadFile').hide();
            }
        }
    });
}

//所有的上传控件集合
var uploaders_cache = {};
/**
 * 初始化上传控件
 * @param container 整个上传区域id,包含附件列表和上传按钮
 * @param btnId 按钮id
 * @param count 限制文件个数
 * @param ext 限制文件后缀
 * @param size 限制文件大小
 * @param listAreaId0 附件列表区域id
 * @param taskId0 
 * @param idsInut id输出框
 */
function pluploadInit(container, btnId, count, ext, size, listAreaId0, taskId0, idsInut, lead, fileType) {
    var fatherDiv = $('#' + container);
    var uploader = new plupload.Uploader({
        browse_button: btnId, // this can be an id of a DOM element or the DOM element itself
        url: nginxRoot + 'web/upload',
        runtimes: 'html5,flash,html4',
        filters: {
            // 每个文件的限制大小
            max_file_size: size + 'mb',
            prevent_duplicates: true,//防止重复
            // 可以选择的文件
            mime_types: [
                //{title : "Image files", extensions : "jpg,gif,png"},
                //{title : "Zip files", extensions : "zip"},
                { title: "All files", extensions: ext }
            ]
        },
        flash_swf_url: contextPath + '/resources/plupload/Moxie.swf',
        file_data_name: 'addfile',//附件data域名字
        //			multipart : false,
        multipart_params: {
            'uid': currentUser.id,
            'sid': _cookid,
            'sessionId': _cookid,
            'taskId': $('#' + taskId0).val()
        }// 随文件上传一同向上传接收程序提交的Post数据
    });
    uploader.init();
//    console.log(uploader.getOption());
    //添加一个文件时执行事件
    uploader.bind('FilesAdded', function (up, files) {
        var dup_names = '';
        //			$.each(up.files,function(i,o){
        //				if(!Utils.isChinese2(o.name)){
        //					up.files[i].name = utf8_decode(o.name);
        //				}
        //			});
        //			files = up.files;
        plupload.each(files, function (file) {
            if (!Utils.isChinese2(file.name)) {
                file.name = utf8_decode(file.name);
            }
            //			  if(!tooMuch){// TODO 校验数量限制
            //				  plupload.removeFile(file);
            //			  }else{
            //				  var total = $('#'+container+' .attachmentListArea').children().length;
            //				  if()
            //			  }
            var dup = false;
            //校验重复上传
            //console.log($(".peopleInB_Success").length);
            if (caseType == "case") {
                if ($(".attContent").length > count) {
                    jAlert("上传文件超过限制");
                    return;
                }
            } else {
                if ($(".peopleInB_Success").length > count) {
                    jAlert("上传文件超过限制");
                    return;
                }
            }
            $('a', fatherDiv).each(function () {
                if ($(this).text() == file.name) {
                    dup = true;
                    uploader.removeFile(file);
                }
            });
            $('.js_name', fatherDiv).each(function () {
                if ($(this).text() == file.name) {
                    dup = true;
                    uploader.removeFile(file);
                }
            });
            if (!dup) {
                if (lead !== 'lead') {
                    showDiv(file, listAreaId0);
                    if (fatherDiv.find('.attachError')) {
                        fatherDiv.find('.attachError').remove();
                    }
                } else {
                    showLead(file, listAreaId0);
                }
            } else {
                dup_names += '、' + file.name;
            }
        });
        if (dup_names != '') {
            dup_names = dup_names.substr(1);
            showErrorMsg(dup_names + ' 文件已经存在，请勿重复上传  或者修改文件名称之后再次尝试');
        } else {
            this.start();//文件添加完毕之后自动开始上传
            this.disableBrowse(true);
        }
//        console.log('FilesAdded');
    });
    uploader.bind('FilesRemoved', function (up, files) {//文件开始上传
        //console.log('FilesRemoved');
        //			console.log(up.state);
        if (up.state == plupload.STOPPED) {
            this.disableBrowse(false);
        }
    });
    uploader.bind('UploadFile', function (up, file) {//文件开始上传
//        console.log('UploadFile');
        $('input[type="submit"]').attr('disabled', 'disabled');
        // setFileState(file.id, '上传中…');
        $('#' + file.id + '_div').text("");
        hideFileName=file.name;
        hideFileId=file.id;
        if (lead !== 'lead') {
            $('<div class="fj-jdt-div"><div class="jdt-div"></div></div><span id="' + file.id + '_span"></span>').appendTo($('#' + file.id + '_div'));
        } else {
            $('<div class="fj-jdt-div1"><div class="jdt-div1"></div></div><span id="' + file.id + '_span"></span>').appendTo($('#' + file.id + '_div'));
        }
    });
    uploader.bind('UploadProgress', function (up, file) {//上传进度
        console.log('UploadProgress');
        var percent = file.percent;
        console.log(percent);
        console.log(file);

        if (fileType == 2) {
        	$("#videoProBar").show();
            $("#lastVideoBar").show();
            $("#videoProBar").css("width",percent + "px");
        }
        percent = (percent > 100 ? 100 : percent);
        $('#' + file.id + '_span').text(percent + '% ');
        $('#' + file.id + '_div div div').css('width', percent + '%');
        if (percent == 100) {
            $('input[type="submit"]').removeAttr('disabled');
        }
        
    });
    uploader.bind('FileUploaded', function (up, file, info) {//上传一个文件完毕
//        console.log('FileUploaded');
        var responses = eval(info);
        var fileIdsDiv = idsInut;
        var fileIds = fileIdsDiv.val();
        var data =eval('(' + responses.response + ')');
        fileIds += data.id + ',';
        $("#imageFile").hide();
		$("#imageFileSpan").hide();

		$("#id2").show();
		$("#span2").show();
		$("#span2").html(file.name);
		if (fileType==1){
			imgPath = data.path;
			$("#column_create_file_div_addImagesButton").html("修改封面");
			$("#picCopy").attr("src", nginxRoot + data.path);
		} else if (fileType==2){
			videoPath = data.path;
			$("#column_create_file_div_addVideosButton").html("修改封面");
			alert("视频上传成功！");
		}
		
    });
    uploader.bind('UploadComplete', function (up, files) {//上传一个文件完毕
        //console.log('UploadComplete');
        this.disableBrowse(false);
    });
    uploader.bind('Error', function (up, err) {
        console.log(err);
        console.log(err.message);
        //showErrorMsg(err.message);

        // 2014.11.25
        // GTZS-1202 资讯——新建资讯——上传附件失败，提示HTTP错误，且没有重新上传功能——qatest——IE10
        // 祁磊：http 错误要改成网络错误
        // 修改人：王子美
        var s = err.code == -200 ? "网络错误导致上传失败,请重新上传\n文件名:"+hideFileName : "";
        $("#"+hideFileId+"_fatherDiv").remove();
        //showErrorMsg(s);
    });
    uploaders_cache[container] = uploader;
    /**
     * 移除一个文件
     * @param fileId 文件id
     * @param fileName 文件名字
     * @param id server端文件id
     * @param obj 
     * @param fileIdsDiv 文件id输出框
     */
    function removeFileDiv(fileId, fileName, id, obj, fileIdsDiv) {
        var _s = uploaders_cache['card_create_file_div']
        for (var i in _s.customSettings.fileQueue) {
            if (_s.customSettings.fileQueue[i].name == fileName) {
                _s.cancelUpload(fileId);
                _s.customSettings.fileQueue.splice(i, 1);
                break;
            }// 防止同名文件重复添加
        }
        $('#' + fileId + '_fatherDiv').remove();
        $.ajax({
            url: dataPath + '/attachment/delete.json',
            data: 'fileId=' + id,
            dataType: 'json',
            cache: false,
            async: false,
            type: 'POST',
            success: function (data) {
                var fileIds = fileIdsDiv.val();
                if (typeof (fileIds) != 'undefined') {
                    fileIds = fileIds.replace(id + ',', '');
                    fileIdsDiv.val(fileIds);
                }
                Utils.resetPopupWrap();
            }
        });
    }
    function showErrorMsg(msg) {
        $.alerts._showMessage(msg, '', '', '', 2000);
    }
}


////////////////////////////////////////////////////////////
//
// plu = plupload
//
// - 王子美

var plu = {};

!function (plu) {
    // 用法：
    // <div class=plufor-nki></div>
    //
    // plu-picture-upload
    // 依赖：jquery, plupload
    //
    // plupload 缺点
    // 1. 运行时 = html4 时初始化比较慢，需要几秒钟，所以可能一开始浏览按钮不响应点击。
    // 2. 在 ie9- 使用的 runtime = html4，要在传入的浏览按钮上盖一个东西，导致浏览按钮的 hover 不正常。
    // 3. uploader 的 id 和生成的 form 及 input 的 id 不一样，不知道自己的浏览按钮和哪个 input 对应。
    // 4. ie8- 不能把生成的 moxie shim 放到隐藏的元素中否则初始化失败
    //
    // new plupload.Uploader 有个必填参数 browse_button，要求是个 html 元素。这要求初始化 plupload 时同时搞一个
    // 作为按钮的元素。初始化会修改按钮和其父元素，给其父元素添加一些元素。
    // setOption("browse_button", [elem, ...]) 可以指定多个浏览按钮，但每个浏览按钮都对应一套上面的东西。
    // 所以 .plu-picture-upload 的每个方块都对应一个 plupload 对象，不共享同一个 plupload 对象。
    //
    // PluploadFile.status = {
    //      4 - plupload.FAILED
    //      5 - plupload.DONE
    // }
    //
    // 进度条用法
    //var tempN = 0, elBusy = $(".busy")[0];
    //setTimeout(function () {
    //    if (tempN < 100) {
    //        tempN += 2;
    //        setTimeout(arguments.callee, 50);
    //    }
    //    plupBlockSetProgress(elBusy, tempN);
    //}, 50);
    //
    // <nginxRoot>          http://file.qatest.gintong.com
    // <knowledgeDataPath>  http://knowledge.qatest.gintong.com:1111
    //
    // 使用 plupload 上传附件
    // <nginxRoot>/knowledge/web/upload
    //
    // 获取附件列表，taskId 是页面上的一个隐藏 input
    // <knowledgeDataPath>/attachment/getFiles.json?taskId=taskId=TVRReE5qYzVNRE0xTlRFME5qQXhPVFU0TkRBeU9EZzM=
    //
    // 下载附件
    // <knowledgeDataPath>/attachment/download?fileIds=13
    // http://knowledge.qatest.gintong.com:1111/attachment/download?fileIds=13
    //
    // 删除附件，post，id 是上传成功返回的 id，也能在获取的文件列表里查询，属性也是 id
    // 删除结果没有提示，传递非数字可能得到 http 500
    // <knowledgeDataPath>/attachment/delete.json?fileId=15
    //
    // /knowledge/file/0040183234
    //
    // plufor-nki 使用的是上传封面而不是上传附件
    //
    // 调试：在 账号 > 我的知识 > 新建知识 页面运行下面函数里的语句
    function xxxDemoOperation() {
        // 获取文件列表
        $.getJSON("http://knowledge.qatest.gintong.com:1111/attachment/getFiles.json?taskId=taskId=TVRReE5qYzVNRE0xTlRFME5qQXhPVFU0TkRBeU9EZzM=",
            function (e) { console.log(e); });
        // 删除指定的文件
        $.post("http://knowledge.qatest.gintong.com:1111/attachment/delete.json?fileId=15", function (e) { console.log(e); });
    }

    function pluPicture(elem) {
        var FILE_TYPE = "jpg,gif,png",  // 逗号间不能有空格
            CP_MINOR = 0,               // falsey。无关紧要的地方
            CP_BODY = 1,                // truthy。
            CP_CLOSE = 2,               // truthy。右上角关闭按钮
            CP_MIDDLE_RIGHT = 3;        // truthy

        var $elem = $(elem),
            theUploadUrl = NGINX_ROOT + "/web/upload",
            theCookie = window._cookid || "ad2a96ab-286a-4160-8ed7-c133f6ec2db7",
            theTask = $("#taskId").val() || "taskId=TVRReE5qYzVNRE0xTlRFME5qQXhPVFU0TkRBeU9EZzM=",
            xxxUseFlash, xxxFlashLeftCoordinate;

        plupClear();
        elem.plupMaxBlocks = 8;
        elem.plupClear = plupClear;
        elem.plupBlockAdd = plupBlockAdd;
        elem.plupBlockToSuccess = plupBlockToSuccess;
        elem.plupDisableBrowse = plupDisableBrowse;
        $elem.addClass("plu-picture-upload");
        return elem;

        ////////////////////////////////////////////////////////////
        //
        // 调用外挂

        function plupBeforeInitUploader(uploader) {
            if (typeof elem.plupBeforeInitUploader == "function")
                elem.plupBeforeInitUploader(uploader);
        }
        function plupOnInitUploader(uploader, obj) {
            if (typeof elem.plupOnInitUploader == "function")
                elem.plupOnInitUploader(uploader);
        }
        function plupOnDeleteFile(uploader, file) {
            if (typeof elem.plupOnDeleteFile == "function")
                elem.plupOnDeleteFile(uploader, file);
        }
        ////////////////////////////////////////////////////////////
        //
        // plupload 初始化

        // 给方块关联一个 uploader，使用上传附件的设置作为选项默认值
        function plupBlockConnectUploader(block) {
            var uploader, $block = $(block), option = {
                //
                // plupload required
                browse_button: $block.children(".browse-button")[0],
                url: theUploadUrl,
                //
                // 金桐网服务端 required
                file_data_name: "filedata", // <input type=file name=filedata>，服务端有类似 $_FILES["filedata"] 的代码
                multipart_params: {
                    sid: theCookie,
                    sessionId: theCookie,
                    taskId: theTask
                },
                //
                // optional
                //container: $pluContainer[0], // HACK: ie9- 要想让 runtime = flash 而不是 html4，不能设置此值，可能是 $pluContainer 隐藏了
                runtimes: "html5, flash", // html4 没法提交表单
                flash_swf_url: "/resources/plupload/Moxie.swf", // 应该有个内容为 static.xxx.gintong.com 的变量做前缀，但不知道该变量是啥
                filters: {
                    max_file_size: "2mb",
                    mime_types: [{ title: FILE_TYPE, extensions: FILE_TYPE }]
                },
                init: {
                    Init: plupOnInit,
                    FilesAdded: plupOnFilesAdded,
                    UploadProgress: plupOnProgress,
                    FileUploaded: plupOnUploaded,
                    Error: plupOnError
                },
                multi_selection: false
            };
            uploader = new plupload.Uploader(option);
            plupBeforeInitUploader(uploader);
            uploader.init();
            uploader.plupBlock = block;
            block.plupUploader = uploader;
        }
        // 如果方块有 plupUploader 属性，先删除 plupUploader 的所有文件然后摧毁 plupUploader
        function plupBlockDisconnectUploader(block) {
            var i, len, uploader = block.plupUploader, file;
            if (uploader) {
                for (i = 0, len = uploader.files.length; i < len; ++i) {
                    file = uploader.files[i];
                    if (file.status == plupload.DONE)
                        plupOnDeleteFile(uploader, file);
                }
                uploader.destroy();
                uploader.plupBlock = null;
                block.plupUploader = null;
                block.plupMoxieShim = null;
            }
        }
        // 使用 flash 时不能隐藏该 flash 否则 maydie
        function plupDisableBrowse(block, disable) {
            block.plupUploader.disableBrowse(disable === true ? undefined : false);
            xxxUseFlash && $(block.plupMoxieShim).css("left", disable ? 1000 : xxxFlashLeftCoordinate);
        }

        ////////////////////////////////////////////////////////////
        //
        // plupload 事件

        // 创建了 moxie shim
        function plupOnInit(uploader, obj) {
            uploader.plupBlock.plupMoxieShim = $(uploader.plupBlock).children(".moxie-shim")[0];
            // HACK: 在 ie9- 中为了让 runtime = flash 而不是 html4，没有设置容器。但不设置容器的后果是 flash 挡在选择
            // 图片的按钮上面，按钮没法响应 hover。所以使用把 flash 挪走的办法来模拟隐藏。
            if (obj.runtime == "flash") {
                xxxUseFlash = true;
                xxxFlashLeftCoordinate = $(uploader.plupBlock.plupMoxieShim).css("left");
            }
            // 设置了 xxxUseFlash 和 xxxFlashLeftCoordinate 后才调用外挂，因为外挂调用了 plupDisableBrowse，该函数使用 xxxUseFlash
            plupOnInitUploader(uploader, obj);
        }
        // 选择了文件
        function plupOnFilesAdded(uploader, files) {
            plupBlockToBusy(uploader.plupBlock);
            uploader.start();
        }
        // 上传进度
        function plupOnProgress(uploader, file) {
            plupBlockSetProgress(uploader.plupBlock, file.percent);
        }
        // 上传成功
        function plupOnUploaded(uploader, file, info) {
            if (typeof elem.plupOnUploaded == "function")
                elem.plupOnUploaded(uploader, file, info);
            else
                plupBlockToSuccess(uploader.plupBlock, "https://www.gravatar.com/avatar/");
        }
        // 上传出错
        function plupOnError(uploader, err) {
            var s = "";
            if (err.code == -200) {
                s = "\n网络错误导致上传失败";
                plupBlockToFail(this.plupBlock); // 失败状态下可以重新上传失败的文件，无需选择
            } else {
                if (err.code == -600)
                    s = "\n最大可以上传 2 MB 的文件";
                else if (err.code == -601)
                    s = "\n接受的文件格式是 " + FILE_TYPE;
                plupBlockResetToReady(this.plupBlock);
            }
            gtAlert(err.message + s);
        }

        ////////////////////////////////////////////////////////////
        //
        // 添加删除方块

        // 新建并添加一个上传图片的方块。不超过 elem.plupMaxBlocks 个。
        // 这里给方块添加了两个事件监听
        function plupBlockAdd() {
            var $block, ret = null;
            if (this.children.length < this.plupMaxBlocks) {
                $block = $("<div><div class=browse-button></div></div>");
                $block.on({
                    click: plupBlockOnClick,
                    mousemove: plupBlockOnMouseMove
                });
                ret = $block[0];
                $elem.append($block);
                plupBlockConnectUploader(ret); // 必须添加到 dom 里后才初始化 plupload，以便让 plupload 能找到 container
                plupBlockToReady(ret);
            }
            return ret;
        }
        // 删除一个方块，删除上传成功的文件，摧毁与之关联的 pluploader
        function plupBlockDel(block) {
            plupBlockDisconnectUploader(block);
            block.parentElement.removeChild(block);
        }
        // 摧毁所有和方块关联的 plupload.Uploader，删除所有方块
        function plupClear() {
            $elem.children().each(function (i, o) {
                plupBlockDisconnectUploader(o);
            });
            $elem.html("");
        }

        ////////////////////////////////////////////////////////////
        //
        // 方块外观

        // 转到就绪状态
        function plupBlockToReady(block) {
            // 如果直接允许浏览，该函数又被 onclick 调用，则会弹出选择文件对话框
            setTimeout(function () { plupDisableBrowse(block, false); }, 0);
            $(block)
                .removeClass("ready busy success fail")
                .addClass("ready")
                .children(".browse-button")
                .html("");
        }
        // 转到上传中状态
        function plupBlockToBusy(block) {
            plupDisableBrowse(block, true);
            plupBlockSetProgress(block, 0);
            $(block)
                .removeClass("ready busy success fail")
                .addClass("busy")
                .children(".browse-button")
                .html("<div class=dodgerblue></div><div class=gainsboro></div><div class=filename></div>")
                .children(".filename")
                .text(block.plupUploader.files[0].name);
        }
        // 转到失败状态
        function plupBlockToFail(block) {
            plupDisableBrowse(block, true);
            $(block)
                .removeClass("ready busy success fail")
                .addClass("fail")
                .children(".browse-button")
                .html("");
        }
        // 转到成功状态
        // 这里需要下载一个图片。如果图片下载成功，居中显示它
        function plupBlockToSuccess(block, src) {
            var $img, picPath = block.plupUploader.files[0].plupResponse.picPath,
                filename = picPath.substring(picPath.lastIndexOf("/") + 1);
            plupDisableBrowse(block, true);
            $(block)
                .removeClass("ready busy success fail")
                .addClass("success")
                .children(".browse-button")
                .html("<div class=mask></div><img>")
                .children(".mask")
                .text(filename);
            $img = $(block).children(".browse-button").children("img");
            $img.on("load", onLoadImage).prop("src", src);
            // img 的 onload 不一定触发
            // http://stackoverflow.com/questions/5933230/javascript-image-onload
            $img.prop("complete") && onLoadImage();
            function onLoadImage() {
                $img.css({
                    "margin-left": (158 - $img.prop("width")) / 2 + 1,
                    "margin-top": (118 - $img.prop("height")) / 2 + 1,
                    visibility: "visible"
                });
            }
        }
        // 转到就绪状态，重新连接 uploader
        function plupBlockResetToReady(block) {
            plupBlockDisconnectUploader(block);
            plupBlockConnectUploader(block);
            plupBlockToReady(block);
        }

        ////////////////////////////////////////////////////////////
        //
        // 方块事件

        // 点击方块
        function plupBlockOnClick(e) {
            var $this = $(this);
            if ($this.hasClass("busy") || $this.hasClass("success")) {
                if (plupBlockCursorPos(e) == CP_CLOSE)
                    plupBlockResetToReady(this);
            } else if ($this.hasClass("fail")) {
                if (plupBlockCursorPos(e) == CP_CLOSE)
                    plupBlockResetToReady(this);
                else if (plupBlockCursorPos(e) == CP_MIDDLE_RIGHT) {
                    plupBlockToBusy(this);
                    this.plupUploader.files[0].status = plupload.QUEUED;
                    this.plupUploader.start();
                }
            }
        }
        // 鼠标移动时设置鼠标形状
        function plupBlockOnMouseMove(e) {
            var $this = $(this), pos = plupBlockCursorPos(e), cursor = "";
            if ($this.hasClass("busy") || $this.hasClass("success"))
                pos == CP_CLOSE && (cursor = "pointer");
            else if ($this.hasClass("fail"))
                (pos == CP_CLOSE || pos == CP_MIDDLE_RIGHT) && (cursor = "pointer");
            $this.css("cursor", cursor);
        }
        // 助手：鼠标在方块的哪个区域
        function plupBlockCursorPos(e) {
            var x = e.offsetX || e.clientX - $(e.target).offset().left + window.pageXOffset,
                y = e.offsetY || e.clientY - $(e.target).offset().top + window.pageYOffset;
            // x = [130, 155], y = [ 5, 30] - 叉号
            // x = [ 85, 140], y = [60, 80] - 重新上传
            if (130 < x && x < 155 && 5 < y && y < 30)
                return CP_CLOSE;
            else if (85 < x && x < 140 && 60 < y && y < 80)
                return CP_MIDDLE_RIGHT;
            else
                return CP_BODY;
        }
        // 助手：busy 方块有进度条。o = 方块；n = [0, 100]，进度
        function plupBlockSetProgress(o, n) {
            if ($(o).hasClass("busy")) {
                if (typeof n != "number" || n < 0) n = 0;
                if (n > 100) n = 100;
                $(o).children(".dodgerblue").css("width", n);
                $(o).children(".gainsboro").css("width", 100 - n);
            }
        }
    }

    ////////////////////////////////////////////////////////////
    //
    // 新建知识 > 上传图片
    // 只有一个方块
    // 依赖：
    //  plu-picture-upload, plupload, jquery
    //  <input id=picPath name=pic>

    // 正常的 block.plupUploader.files 数组里面都是 PluploadFile，我给它推入一个 object = {
    //  plupResponse    - 我的代码使用
    //  status          - 我的代码使用
    //  destroy         - plupload 使用
    // }
    function pluNki(elem) {
        var theUploadUrl = NGINX_ROOT + "/knowledge/nginx/cover/upload/",
            picPath = $("#picPath").val(), block;
        pluPicture(elem);
        elem.plupBeforeInitUploader = beforeInitUploader;
        elem.plupOnInitUploader = onInitUploader;
        elem.plupOnUploaded = onUploaded;
        elem.plupOnDeleteFile = onDeleteFile;
        block = elem.plupBlockAdd();
        if (picPath) {
            block.plupUploader.files.push({
                plupResponse: { picPath: picPath },
                status: plupload.DONE,
                destroy: function () { }
            });
            elem.plupBlockToSuccess(block, NGINX_ROOT + picPath);
        }
        return elem;

        function beforeInitUploader(uploader) {
            var option = uploader.getOption(),
                sessionId = option.multipart_params["sessionId"];
            uploader.setOption({
                url: theUploadUrl,
                file_data_name: "upfile",
                send_file_name: false,
                multipart_params: { sessionId: sessionId }
            });
        }
        // 如果隐藏的 input 有值说明要显示上次的上传结果，这时候应该是成功状态并禁用浏览。这里是 pluNki 的 if (picPath) 的延续，
        // 之所以放在这里而不放在 if (picPath) 中是因为到这里 plupload 才初始化完毕，调用 uploader.disableBrowse 才有效
        function onInitUploader(uploader, obj) {
            $("#picPath").val() && elem.plupDisableBrowse(block, true);
        }
        // 上传成功，json = {
        //  fileRealName: "blue-pencil.png",
        //  picPath: "/knowledge/cover/0040183625.jpg",
        //  url: "/knowledge/cover/0040183625_small_edit.jpg",
        //  pictitle: "",
        //  state: "SUCCESS",
        //  imgid: "14165640587431362",
        //  width: "16",
        //  height: "16"
        //}
        // 这里需要手工调用 plupBlockToSuccess，因为该过程需要下载一个图片，图片的 url 是拼接出来的，存储时只存储后半部分，
        // 这造成要想在 pluPicture 中统一处理会比较麻烦。
        function onUploaded(uploader, file, info) {
            var response = $.parseJSON(info.response);
            $("#picPath").val(response.picPath);
            file.plupResponse = response;
            elem.plupBlockToSuccess(uploader.plupBlock, NGINX_ROOT + response.picPath);
        }
        function onDeleteFile(uploader, file) {
            // knowledge.qatest.gintong.com:1111/baike/image/delete?picList=[{"_id":"14166381620613324"}]
            if (file.plupResponse.imgid)
                $.post(KNOWLEDGE_PATH + "/baike/image/delete?picList=%5B%7B_id:'" + file.plupResponse.imgid + "'%7D%5D");
            $("#picPath").val("");
            file.plupResponse = null;
        }
    }

    //
    // utility

    function gtAlert(string) {
        $.alerts && $.alerts._showMessage ? $.alerts._showMessage(string, "", "", "", 2000) : alert(string);
    }

    function touchKnown(theClass, f) {
        $("." + theClass).each(function (i, o) {
            $(o).removeClass(theClass);
            f(o);
        });
    }

    //
    // exec

    var NGINX_ROOT = typeof nginxRoot == "string" ? nginxRoot : "http://file.qatest.gintong.com",
        KNOWLEDGE_PATH = typeof knowledgeDataPath == "string" ? knowledgeDataPath : "http://knowledge.qatest.gintong.com:1111";

    touchKnown("plufor-picture-upload", pluPicture);
    touchKnown("plufor-nki", pluNki); // new knowledge image

    plu.picture = pluPicture;
    plu.nki = pluNki;
}(plu);