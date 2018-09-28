$(document).ready(function(){
/**
 * TA柜台业务页面涉及到份额显示的组件：
 * 1.份额冻结的可冻份额
 * 2.份额解冻的冻结份额
 * 3.份额强增、强减的份额余额
 * @author tuojg12929
 * @create 2016/3/15
 */

$.WebForm.uiRender("AvailableFrozenShareInput", function($availableFrozenShareInput, webForm){
    var uiName = $availableFrozenShareInput.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue*1);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    $availableFrozenShareInput.on("refresh", function(_, value) {
        //if(value){
        var c_tacode=webForm.getValue("c_tacode");
        var c_fundacco=webForm.getValue("c_fundacco");
        var c_agencyno=webForm.getValue("c_agencyno");
        var c_netno=webForm.getValue("c_netno");
        var c_tradeacco=webForm.getValue("c_tradeacco");
        var c_fundcode=webForm.getValue("c_fundcode");
        var c_sharetype=webForm.getValue("c_sharetype");
        var c_oricserialno=webForm.getValue("c_oricserialno");
        var c_orirequestno=webForm.getValue("c_orirequestno");
        var showconfigCode = $availableFrozenShareInput.parents('form').attr("showConfigCode");

        webForm.loadData(uiName, {c_tacode:c_tacode,c_fundacco:c_fundacco,c_agencyno:c_agencyno,c_netno:c_netno,
            c_tradeacco:c_tradeacco, c_fundcode:c_fundcode,c_sharetype:c_sharetype,c_oricserialno:c_oricserialno,
            c_orirequestno:c_orirequestno,showconfigCode:showconfigCode
        }, function(data) {
            var uiValue = '';
            if (data.length != 0) {
                if(uiName == 'f_remainincome'){
                    uiValue = data[0]["f_income"];
                    if (uiValue == null) {
                        webForm.setValue(uiName, "");
                    } else {
                        webForm.setValue(uiName, uiValue);
                    }
                }else{
                    uiValue = data[0]["f_share"];
                    if (uiValue == null) {
                        webForm.setValue(uiName, "");
                    } else {
                        webForm.setValue(uiName, uiValue);
                    }
                }

            }
        });
        //}
    });
    $availableFrozenShareInput.on("input keypress", function() {
        var uiValue = webForm.getValue(uiName);

        var newValue = $availableFrozenShareInput.val();
        if(newValue==uiValue) {
            return; // 未修改
        }

        webForm.setValue(uiName, newValue);
    });

    $availableFrozenShareInput.on("value", function(_, value) {
        $availableFrozenShareInput.val(value);
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $availableFrozenShareInput.trigger("chosen:updated");
    });
});

/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/3/15
 */
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/3/15
 */
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2016/3/30
 */

$.WebForm.uiRender("AvailableShareInput", function($availableShareInputs, webForm){
    var uiName = $availableShareInputs.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue*1);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    $availableShareInputs.on("refresh", function() {
        var c_tacode = webForm.getValue("c_tacode");
        var c_fundacco = webForm.getValue("c_fundacco");
        var c_agencyno = webForm.getValue("c_agencyno");
        var c_netno = webForm.getValue("c_netno");
        var c_tradeacco = webForm.getValue("c_tradeacco");
        var c_fundcode = webForm.getValue("c_fundcode");
        var c_sharetype = webForm.getValue("c_sharetype");
        var uiValue = '0.00';
        if(c_sharetype != '' && c_tradeacco != ''){
            webForm.loadData(uiName, {c_tacode:c_tacode,c_fundacco:c_fundacco,c_agencyno:c_agencyno,c_netno:c_netno,
                c_tradeacco:c_tradeacco, c_fundcode:c_fundcode,c_sharetype:c_sharetype
            }, function(data) {

                if (data.length != 0) {
                    uiValue = data[0].f_share = null ? '0.00':data[0].f_share.toFixed(2);
                }
                webForm.setValue(uiName, uiValue);
            });
        }else{
            webForm.setValue(uiName, uiValue);
        }
        //}
    });

    $availableShareInputs.on("value", function(_, value) {
        $availableShareInputs.val(value);
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $availableShareInputs.trigger("chosen:updated");
    });
});
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2016/3/30
 */
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2016/3/30
 */
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2016/4/6
 */
$.WebForm.uiRender("BaseMutiSvcDicSelect", function($dictionarySelect, webForm) {
    var uiName = $dictionarySelect.attr("ui-name");
    var readonly = $dictionarySelect.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $dictionarySelect.find("option:first").val());
    }
    if(readonly=="readonly") {
        $dictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $dictionarySelect.on("change", function() {
        uiValue = $dictionarySelect.val();
        if(readonly!="readonly") {
            //
            webForm.setValue(uiName,uiValue);
        }
    });
    $dictionarySelect.on("filter", function(_, code) {
        var filterFlag = "1";
        var  c_fundcode= webForm.getValue("c_fundcode");

        webForm.loadData(uiName,{filterFlag:filterFlag,c_fundcode:c_fundcode,code:code},function(result){
            uiValue = result.length>0?result[0][uiName]:"";
            webForm.setValue(uiName,uiValue);
            var uiEvent = {
                name: "selectChange",
                data: {
                    value: uiValue
                }
            };
            webForm.triggerEvent(uiName,uiEvent);
            $dictionarySelect.trigger("chosen:updated");
        })
    });
    //
    $dictionarySelect.on("value", function(_, value) {
        /*$dictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $dictionarySelect.val(value);
        var uiLabel = $dictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $dictionarySelect.trigger("chosen:updated");
        /*$dictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $dictionarySelect.chosen({
        search_contains: true,
        no_results_text: "没有匹配项",
        placeholder_text:"请选择",
        allow_single_deselect: true,//可删除已经选择的项
        disable_search_threshold: 2,
    }).change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $dictionarySelect.addClass("chosen-select");
});
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2016/4/6
 */
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2016/4/6
 */
/**
 * 日期时间输入组件
 *
 * @author Gavin Hu
 * @create 2015/11/17
 */
$.WebForm.uiRender("DateTimeInput", function($dateTimeInput, webForm) {
    var uiName = $dateTimeInput.attr("ui-name");
    var uiValue = $dateTimeInput.attr("ui-value");
    if(!!uiValue){
        var date = uiValue.split('-').join('');
        if(date.length == 8){
            $dateTimeInput.val(date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8));
        }
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    //
    var pad = function(num, n) { //月 和 日 用0补全 num 初始数据 n 补全后位数
        if(!!num){
            return Array(n>num.length?(n-(''+num).length+1):0).join(0)+num;
        }
        return '';
    };
    var dateChange = function(date){
 	var uiEvent = {
            name: "dateChange",
            data: {
                value: date
            }
        };
        //
        var formdate = date.split('-');
        webForm.setInternalValue(uiName, formdate[0] + pad(formdate[1],2) +pad(formdate[2],2));
        //
	webForm.triggerEvent(uiName, uiEvent);
    };
    var dateFormat = function(date){ // 日期格式化，将YYYYMMDD格式自动转换为YYYY-MM-DD
        // 日期格式 不带'-'
        var dateformat = /^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8])))$/;
        //日期格式 带'-'
        var dateIOSformat = /^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))$/;

        if(dateformat.test(date)){
            date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);

            $dateTimeInput.val(date);

        }
        return date;
    };
    var setValues = function(webForm,uiName, uiValue){
        webForm.context.uiValues[uiName] = uiValue;
        //
        var $ui = webForm.context.uiObjects[uiName];
        //
        $ui.trigger("value", [uiValue]);
    }
    //
    $dateTimeInput.on("click", function() {
        var uiValue = webForm.getValue(uiName);
        var settings = {
            choose: function(date) {
                //
                if(date==uiValue) {
                    return ; // 未修改
                }
                //
                dateChange(date);
            }
        };
        //
        $.extend(settings, webForm.getMeta(uiName));
        //
        laydate(settings);
        //
        $("#laydate_ok,#laydate_today,#laydate_clear").on("click", function(){
            var date = $dateTimeInput.val();
            //
            dateChange(date);
        });
    });
    $dateTimeInput.on("filter", function(_, tacode){
        $.ajaxSetup({
            async: false
        });
        //
        if(uiName=="d_contractenddate"){ //清盘方案设置时特殊处理，入参并不是主动事件的值
            tacode = webForm.getValue("c_fundcode");
        }
        if(tacode) {
            webForm.loadData(uiName, {tacode:tacode}, function(data){
                if(data.length>0){
                    $dateTimeInput.val(data[0].item);
                    webForm.setInternalValue(uiName, data[0].item.split('-').join(''));
                }
            });
        }
        $.ajaxSetup({
            async: true
        });

    });
    //
    $dateTimeInput.on("input", function(){

        var date = dateFormat($dateTimeInput.val());
        //
        dateChange(date);
    });
    //
    $dateTimeInput.on("value", function(_, value){
        $dateTimeInput.val(value);
    });

    $dateTimeInput.on("dateChange",function(){
        var thisForm=$(this).parents().find("form");
        var validator = thisForm.validate();
        validator.element($(this));
    });
});
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/17
 */

$.WebForm.eventAdapter("DateTimeInput", "dateChange", "FilterDictionarySelect", "filter", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/17
 */
/**
 * TODO description here
 *日期区间组件
 * @author xiaoya10584
 * @create 2016/6/4
 */
$.WebForm.uiRender("DateTimeSectionInput", function($dateTimeSectionInput, webForm) {
    var uiName = $dateTimeSectionInput.attr("ui-name");

    var dateName1='input[name='+uiName+'1]';
    var dateName2='input[name='+uiName+'2]';
    var $dateTimeSectionInput1=$dateTimeSectionInput.children(dateName1);
    var $dateTimeSectionInput2=$dateTimeSectionInput.children(dateName2);
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    var today = laydate.now(0);
    //
    var dateChange = function(){
        //
        var dateValue=[];
        var dateValue1=$dateTimeSectionInput1.val().split('-').join('');
        var dateValue2=$dateTimeSectionInput2.val().split('-').join('');
        dateValue.push(dateValue1);
        dateValue.push(dateValue2);
        webForm.setValue(uiName,dateValue );
        //

    };
    var setValues = function(webForm,uiName, uiValue){
        webForm.context.uiValues[uiName] = uiValue;
        //
        var $ui = webForm.context.uiObjects[uiName];
        //
        $ui.trigger("value", [uiValue]);
    }
    //
    $dateTimeSectionInput1.on("click", function() {
        var limitValue = (""==$dateTimeSectionInput2.val())?"2099-12-31":$dateTimeSectionInput2.val();
        var istoday = ($dateTimeSectionInput2.val() < today)?false:true;
        var uiValue = webForm.getValue(uiName);
        var settings = {
            max: limitValue,
            istoday: istoday, //是否显示今天
            choose: function(date) {
                //
                if(date==uiValue) {
                    return ; // 未修改
                }
                //
                dateChange();
            }
        };
        //
        $.extend(settings, webForm.getMeta(uiName));
        //
        laydate(settings);
        //
        $("#laydate_ok,#laydate_today,#laydate_clear").on("click", function(){
            var date = $dateTimeSectionInput.val();
            //
            dateChange();
        });
    });

    $dateTimeSectionInput2.on("click", function() {
        var limitValue = (""==$dateTimeSectionInput1.val())?"1990-01-01":$dateTimeSectionInput1.val();
        var istoday = ($dateTimeSectionInput1.val() > today)?false:true;
        var uiValue = webForm.getValue(uiName);
        var settings = {
            min: limitValue,
            istoday: istoday,
            choose: function(date) {
                //
                if(date==uiValue) {
                    return ; // 未修改
                }
                //
                dateChange();
            }
        };
        //
        $.extend(settings, webForm.getMeta(uiName));
        //
        laydate(settings);
        //
        $("#laydate_ok,#laydate_today,#laydate_clear").on("click", function(){
            var date = $dateTimeSectionInput.attr("value","");
            //
            dateChange();
        });
    });

    $dateTimeSectionInput.on("filter", function(_, tacode){
        $.ajaxSetup({
            async: false
        });
        //
        if(tacode) {
            webForm.loadData(uiName, {tacode:tacode}, function(data){
                if(data.length>0){
                    var trimValue=$.trim(data[0].item);
                    $dateTimeSectionInput1.val(trimValue);
                    $dateTimeSectionInput2.val(trimValue);
                    webForm.setValue(uiName,[trimValue.split('-').join(''),trimValue.split('-').join('')]);
                }
            });
        }
        $.ajaxSetup({
            async: true
        });

    });
    //
    $dateTimeSectionInput1.on("input", function(){

        var date = $dateTimeSectionInput.val();
        //
        dateChange();
    });

    $dateTimeSectionInput2.on("input", function(){
        var date = $dateTimeSectionInput.val();
        //
        dateChange();
    });

    //
    $dateTimeSectionInput.on("value", function(_, value){


        $dateTimeSectionInput.val(value);
        var uiEvent = {
            name: "dateChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $dateTimeSectionInput.trigger("chosen:updated");
    });
});
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/6/4
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/6/4
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/4/18
 */
$.WebForm.uiRender("DefaultAllMultipeDictionarySelect", function ($defaultAllMultipeDictionarySelect, webForm) {
    var uiName = $defaultAllMultipeDictionarySelect.attr("ui-name");
    var readonly = $defaultAllMultipeDictionarySelect.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $defaultAllMultipeDictionarySelect.find("option:first").val());
    }
    if (readonly == "readonly") {
        $defaultAllMultipeDictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $defaultAllMultipeDictionarySelect.on("change", function () {

        var uiValue = $defaultAllMultipeDictionarySelect.val();

        if (uiValue == null) {
            uiValue = "";
        }else{

            $defaultAllMultipeDictionarySelect.find("option").each(function(){
                var optionValue=$(this).val();
                if(optionValue==""){
                    $(this).attr("selected",false);
                }
            });
        }

        var selectedValue=$defaultAllMultipeDictionarySelect.children("option:selected").val();
        if (selectedValue == '*') {
            $(this).find("option").each(function(){
                var optionValue=$(this).val();
                if(optionValue!='*'){
                    $(this).attr("disabled",true);
                    $(this).attr("selected",false);
                    var index = uiValue.indexOf(optionValue);
                    if (index > -1) {
                        uiValue.splice(index, 1);
                    }
                }
            });


        }
        else{
            $(this).find("option").each(function(){
                if($(this).val()!='*'){
                    $(this).attr("disabled",false);

                }
            });
        }





        if (readonly != "readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }


        $defaultAllMultipeDictionarySelect.trigger("chosen:updated");

    });
    $defaultAllMultipeDictionarySelect.on("filter", function () {
        var uiValue = $defaultAllMultipeDictionarySelect.val();
        var uiLabel = $defaultAllMultipeDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: uiValue
            }
        };
        if (readonly != "readonly") {
            webForm.setInternalValue(uiName, uiEvent.data.value);
            //
            webForm.triggerEvent(uiName, uiEvent);
        }
    });
    //
    $defaultAllMultipeDictionarySelect.on("value", function (_, value) {
        /*$defaultAllMultipeDictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $defaultAllMultipeDictionarySelect.val(value);
        var uiLabel = $defaultAllMultipeDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $defaultAllMultipeDictionarySelect.trigger("chosen:updated");
        /*$defaultAllMultipeDictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $defaultAllMultipeDictionarySelect.chosen().change(function () {
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $defaultAllMultipeDictionarySelect.addClass("chosen-select");
});



/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/4/18
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/4/18
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/10/15
 */


$.WebForm.uiRender("DefaultAllMultipeFilterDictionarySelect", function($defaultAllMultipeFilterDictionarySelect, webForm) {
    var uiName = $defaultAllMultipeFilterDictionarySelect.attr("ui-name");
    var readonly = $defaultAllMultipeFilterDictionarySelect.attr("readonly");
    if(readonly=="readonly") {
        $defaultAllMultipeFilterDictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $defaultAllMultipeFilterDictionarySelect.on("change", function() {
        var uiValue = $defaultAllMultipeFilterDictionarySelect.val();
        if(uiValue==null){
            uiValue="";
        }
        else{

            $defaultAllMultipeFilterDictionarySelect.find("option").each(function(){
                var optionValue=$(this).val();
                if(optionValue==""){
                    $(this).attr("selected",false);
                }
            });
        }

        var selectedValue=$defaultAllMultipeFilterDictionarySelect.children("option:selected").val();
        if (selectedValue == '*') {
            $(this).find("option").each(function(){
                var optionValue=$(this).val();
                if(optionValue!='*'){
                    $(this).attr("disabled",true);
                    $(this).attr("selected",false);
                    var index = uiValue.indexOf(optionValue);
                    if (index > -1) {
                        uiValue.splice(index, 1);
                    }

                }
            });


        }
        else{
            $(this).find("option").each(function(){
                if($(this).val()!='*'){
                    $(this).attr("disabled",false);

                }
            });
        }


        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }

        $defaultAllMultipeFilterDictionarySelect.trigger("chosen:updated");
    });
    $defaultAllMultipeFilterDictionarySelect.on("filter", function(_, filterValue){

        //
        if($defaultAllMultipeFilterDictionarySelect.attr("disabled")){
            return;
        }
        var $webForm = $.extend({}, webForm.getValues());
        $webForm.code=filterValue;

        webForm.loadData(uiName,  $webForm, function(data){
            $defaultAllMultipeFilterDictionarySelect.empty();
            if(data.length != 0){
                for(var i in data) {
                    var opt = data[i];
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($defaultAllMultipeFilterDictionarySelect);
                }
                webForm.setValue(uiName, '');
            }

            $defaultAllMultipeFilterDictionarySelect.trigger("chosen:updated");
            /*var validator =$defaultAllMultipeFilterDictionarySelect.parents().find("form").validate();
             validator.element($defaultAllMultipeFilterDictionarySelect);*/

        });
    });
    //
    $defaultAllMultipeFilterDictionarySelect.on("value", function(_, value) {
        /*$defaultAllMultipeFilterDictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $defaultAllMultipeFilterDictionarySelect.val(value);
        var uiLabel = $defaultAllMultipeFilterDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $defaultAllMultipeFilterDictionarySelect.trigger("chosen:updated");
        /*$defaultAllMultipeFilterDictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $defaultAllMultipeFilterDictionarySelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $defaultAllMultipeFilterDictionarySelect.addClass("chosen-select");
});




/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/10/15
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/10/15
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/10/11
 */
$.WebForm.uiRender("DefaultAllNotAllMultipeDictionarySelect", function($defaultAllNotAllMultipeDictionarySelect, webForm) {
    var uiName = $defaultAllNotAllMultipeDictionarySelect.attr("ui-name");
    var readonly = $defaultAllNotAllMultipeDictionarySelect.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $defaultAllNotAllMultipeDictionarySelect.find("option:first").val());
    }
    if(readonly=="readonly") {
        $defaultAllNotAllMultipeDictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $defaultAllNotAllMultipeDictionarySelect.on("change", function() {
        var uiValue = $defaultAllNotAllMultipeDictionarySelect.val();
        if(uiValue==null){
            uiValue="";
        }

        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });
    $defaultAllNotAllMultipeDictionarySelect.on("filter", function() {
        var uiValue = $defaultAllNotAllMultipeDictionarySelect.val();
        var uiLabel = $defaultAllNotAllMultipeDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: uiValue
            }
        };
        if(readonly!="readonly") {
            webForm.setInternalValue(uiName, uiEvent.data.value);
            //
            webForm.triggerEvent(uiName, uiEvent);
        }
    });
    //
    $defaultAllNotAllMultipeDictionarySelect.on("value", function(_, value) {
        /*$defaultAllNotAllMultipeDictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $defaultAllNotAllMultipeDictionarySelect.val(value);
        var uiLabel = $defaultAllNotAllMultipeDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $defaultAllNotAllMultipeDictionarySelect.trigger("chosen:updated");
        /*$defaultAllNotAllMultipeDictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $defaultAllNotAllMultipeDictionarySelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $defaultAllNotAllMultipeDictionarySelect.addClass("chosen-select");
});

/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/10/11
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/10/11
 */
/**
 * TODO description here
 *
 * @author xiaoya
 * @create 2015/12/18
 */


$.WebForm.uiRender("DefaultAllSelect", function($defaultAllSelect, webForm) {
    var uiName = $defaultAllSelect.attr("ui-name");
    var readonly = $defaultAllSelect.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $defaultAllSelect.find("option:first").val());
    }
    if(readonly=="readonly") {
        $defaultAllSelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $defaultAllSelect.on("change", function() {
        var uiValue = $defaultAllSelect.val();

        if(readonly!="readonly") {
            webForm.setValue(uiName,uiValue);
            //
        }
    });
    //
    $defaultAllSelect.on("value", function(_, value) {
        $defaultAllSelect.val(value);
        var uiLabel = $defaultAllSelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $defaultAllSelect.trigger("chosen:updated");
    });

    $defaultAllSelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $defaultAllSelect.addClass("chosen-select");
});


/**
 * TODO description here
 *
 * @author chenxue
 * @create 2015/12/7
 */
$.WebForm.eventAdapter("DefaultAllSelect", "selectChange", "MultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("DefaultAllSelect", "selectChange", "NotAllMultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author chenxue
 * @create 2015/12/7
 */
/**
 * 字典项选择组件 (Checkbox模式)
 *
 * @author Gavin Hu
 * @create 2015/11/16
 */
$.WebForm.uiRender("DictionaryCheckbox", function($dictionaryCheckbox, webForm) {
    var uiName = $dictionaryCheckbox.attr("ui-name");
    //
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    var dictItemChange = function() {
        var values = [];
        $dictionaryCheckbox.find(":checked").each(function(){
            values.push($(this).val());
        });
        //
        var newValue = values.join(",");
        //
        webForm.setValue(uiName, newValue);
        //
    };
    //
    $dictionaryCheckbox.on("click", "a", function() {
        var checked = $(this).attr("checked");
        if(checked) {
            checked = false;
        } else {
            checked = true;
        }
        $(this).attr("checked", checked);
        //
        $dictionaryCheckbox.find("input[type=checkbox]").each(function() {
            $(this).prop("checked", checked);
        });
        //
        dictItemChange();
    });
    //
    $dictionaryCheckbox.on("change", "input[type=checkbox]", function(){
        dictItemChange();
    });
    //
    $dictionaryCheckbox.on("value", function(_, value){
        var values = value.split(",");
        $dictionaryCheckbox.find("input[type=checkbox]").each(function(){
            var checked = false;
            for(var i in values) {
                if($(this).val()==values[i]) {
                    checked = true;
                }
            }
            //
            $(this).prop("checked", checked);
        });
        var uiEvent = {
            name: "checkboxChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $dictionaryCheckbox.trigger("chosen:updated");
    });
});
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/16
 */
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/16
 */
/**
 * 字典选择组件 (Radio 模式)
 *
 * @author Gavin Hu
 * @create 2015/11/14
 */
$.WebForm.uiRender("DictionaryRadio", function($dictionaryRadio, webForm){
    var uiName = $dictionaryRadio.attr("ui-name");
    //
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    $dictionaryRadio.on("click", "span", function() {
        $(this).addClass("select-option-selected").siblings().removeClass("select-option-selected");
        //
        $dictionaryRadio.find("input[type=hidden]").attr("disabled", true);
        $(this).find("input[type=hidden]").attr("disabled", false);
        // trigger event
        var newValue = $(this).find("input[type=hidden]").val();

        //
        webForm.setValue(uiName, newValue);
        //
    });
    //
    $dictionaryRadio.on("value", function(_, value) {
        $dictionaryRadio.find("input[type=hidden]").attr("disabled", true).each(function(){
            if($(this).val()==value) {
                $(this).attr("disabled", false);
                $(this).parents("span").addClass("select-option-selected").siblings().removeClass("select-option-selected");
            }
        });
        var uiEvent = {
            name: "radioChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $dictionaryRadio.trigger("chosen:updated");
    });
});

/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/14
 */
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/14
 */
/**
 * 字典项选择组件 (Select模式)
 *
 * @author gavin
 * @create 2015/11/13
 */
$.WebForm.uiRender("DictionarySelect", function($dictionarySelect, webForm) {
    var uiName = $dictionarySelect.attr("ui-name");
    var readonly = $dictionarySelect.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $dictionarySelect.find("option:first").val());
    }
    if(readonly=="readonly") {
        $dictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $dictionarySelect.on("change", function() {
        var uiValue = $dictionarySelect.val();
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //

        }
    });
    $dictionarySelect.on("filter", function() {
        var uiValue = $dictionarySelect.val();
        var uiLabel = $dictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: uiValue
            }
        };
        if(readonly!="readonly") {
            webForm.setInternalValue(uiName, uiEvent.data.value);
            //
            webForm.triggerEvent(uiName, uiEvent);
        }
    });
    //
    $dictionarySelect.on("value", function(_, value) {
        /*$dictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $dictionarySelect.val(value);
        var uiLabel = $dictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $dictionarySelect.trigger("chosen:updated");
        /*$dictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $dictionarySelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $dictionarySelect.addClass("chosen-select");
});

/**
 * 字典选择组件事件适配
 *
 * @author gavin
 * @create 2015/11/13
 */

$.WebForm.eventAdapter("DictionarySelect", "selectChange", "DictionarySelect", "filter", function(data){
    return data.value;
});

$.WebForm.eventAdapter("DictionarySelect", "selectChange", "FundCodeSelect", "filter", function(data){
    return data.value;
});

$.WebForm.eventAdapter("DictionarySelect","selectChange","FundAccoInput","refresh",function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect", "selectChange", "DateTimeInput", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect","selectChange","TradeCommonSelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect","selectChange","TextInput","refresh",function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect","selectChange","RequestNoInput","refresh",function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect","selectChange","IsDisableInput","refresh",function(data){
    return data.value;
});

$.WebForm.eventAdapter("DictionarySelect","selectChange","FilterDictionarySelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect","selectChange","MultipeFilterDictionarySelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect","selectChange","NotAllMultipeFilterDictionarySelect","filter",function(data){
    return data.value;
});

$.WebForm.eventAdapter("DictionarySelect","selectChange","DefaultAllMultipeFilterDictionarySelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect","selectChange","EditableDicSelect","filter",function(data){
    return data.value;
});

$.WebForm.eventAdapter("DictionarySelect","selectChange","VariedTextInput","refresh",function(data){
    return data.value;
});

$.WebForm.eventAdapter("DictionarySelect","selectChange","NetValueIncomeInput","refresh",function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect","selectChange","BaseMutiSvcDicSelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect","selectChange","MutiSourceDicSelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect","selectChange","MutiSourceDicSelect","refresh",function(data){
    return data.value;
});

$.WebForm.eventAdapter("DictionarySelect","selectChange","DateTimeSectionInput","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect","selectChange","FilterFundCodeSelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect","selectChange","MultipeStringFilterDictionarySelect","filter",function(data){
    return data.value;
});

$.WebForm.eventAdapter("DictionarySelect", "selectChange", "FilterGradeFundSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect", "selectChange", "FilterNoDftDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("DictionarySelect", "selectChange", "FilterNoDftDictionarySelect", "refresh", function(data){
    return data.value;
});

$.WebForm.eventAdapter("DictionarySelect", "selectChange", "MultipleDicSelect", "filter", function(data){
    return data.value;
});




/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/13
 */
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2016/4/6
 */
$.WebForm.uiRender("EditableDicSelect", function($dictionarySelect, webForm) {
    var uiName = $dictionarySelect.attr("ui-name");
    var readonly = $dictionarySelect.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $dictionarySelect.find("option:first").val());
    }
    if(readonly=="readonly") {
        $dictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $dictionarySelect.on("change", function() {
        if(readonly!="readonly") {
            if($dictionarySelect.attr('disabled')){
                uiValue = '';
                webForm.setInternalValue(uiName,uiValue);
            }else{
                uiValue = $dictionarySelect.val();
                webForm.setValue(uiName,uiValue);
            }
        }
    });
    $dictionarySelect.on("filter", function(_, code) {
        var filterFlag = "1";
        webForm.loadData(uiName,{filterFlag:filterFlag,code:code},function(result){
            var editable = result.length>0?result[0]['editable']==1:0;
            if(editable){
                $dictionarySelect.attr("disabled",false);
                webForm.setValue(uiName,'');
                $dictionarySelect.trigger("chosen:updated");
            }else{
                $dictionarySelect.attr("disabled",true);
                webForm.setValue(uiName,'');
                $dictionarySelect.trigger("chosen:updated");
            }
            $dictionarySelect.change();
        })
    });

    $dictionarySelect.on("refresh", function(_, code){
        $.ajaxSetup({
            async: false
        });
        //
        webForm.loadData(uiName, { 'code':code }, function(data){
            var value;
            $dictionarySelect.empty();
            $('<option value=""></option>').appendTo($dictionarySelect);
            if(data.length != 0){
                for(var i in data) {
                    var opt = data[i];
                    if(opt.item==webForm.getValue(uiName)){
                        value = webForm.getValue(uiName);
                    }
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($dictionarySelect);
                }
                if(value)
                {
                    webForm.setValue(uiName, value);
                }
                else{
                    webForm.setValue(uiName, data[0].item);
                }
            }
        });
        $.ajaxSetup({
            async: true
        });
        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $dictionarySelect.trigger("chosen:updated");
    });

    //
    $dictionarySelect.on("value", function(_, value) {
        /*$dictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $dictionarySelect.val(value);
        var uiLabel = $dictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $dictionarySelect.trigger("chosen:updated");
        /*$dictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $dictionarySelect.chosen().change(function(){
        if(!$dictionarySelect.attr('disabled')){
            var validator = $(this).parents().find("form").validate();
            validator.element($(this));
        }
    });

    $dictionarySelect.addClass("chosen-select");
});
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2016/4/6
 */

$.WebForm.eventAdapter("EditableDicSelect", "selectChange", "DateTimeInput", "filter", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2016/4/6
 */
/**
 * TODO description here
 *文本区间
 * @author xiaoya10584
 * @create 2016/8/18
 */

$.WebForm.uiRender("FileSelect", function ($fileSelect, webForm) {
    var uiName = $fileSelect.attr("ui-name");
    var textName = 'input[name=' + uiName + '-input]';
    var btnName = 'input[name=' + uiName + '-btn]';
    var $textInput = $fileSelect.children(textName);
    var $btnInput = $fileSelect.find(btnName);
    //
    $btnInput.on("change", function () {
        var uiValue = webForm.getValue(uiName);
        if(this.files.length > 0){
            var fileObj = this.files[0];
            if (fileObj == uiValue) {
                return; // 未修改
            }

            webForm.setValue(uiName, fileObj);

            //路径显示
            $textInput.val(getPath(this));
            var $sheetSelect = $('select[name=c_sheet]');
            $sheetSelect.empty();
            webForm.setValue('c_sheet', '');
            //sheet页获取
            var fileName = fileObj.name;
            var suffix = fileName.substr(fileName.lastIndexOf('.')+1, fileName.length);
            if(suffix.toLowerCase() == 'xls' || suffix.toLowerCase() == 'xlsx') {

                var url = getRootPath() + "common/maintain/getTASheet.htm";
                var form = new FormData();
                form.append("file", fileObj);// 文件对象
                // XMLHttpRequest 对象
                var xhr = new XMLHttpRequest();
                xhr.open("post", url, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        var result = $.parseJSON(xhr.responseText);
                        if (result.success == true) {
                            var data = result.data;
                            for (var key in data) {
                                if(key == 0){
                                    $('<option value="' + key + '" selected = "selected">' + key + ':' + data[key] + '</option>').appendTo($sheetSelect);
                                }else{
                                    $('<option value="' + key + '">' + key + ':' + data[key] + '</option>').appendTo($sheetSelect);
                                }
                            }
                            if(data){
                                webForm.setValue('c_sheet', "0");
                            }
                        }
                    }else{
                        $sheetSelect.empty();
                        webForm.setValue('c_sheet', '');
                    }
                    $sheetSelect.trigger("chosen:updated");
                };
                xhr.send(form);
            }
        }
    });

    $fileSelect.on("value", function (_, value) {
        $fileSelect.val(value);
        var uiEvent = {
            name: "selectChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $fileSelect.trigger("chosen:updated");
    });

    function getPath(obj) {
        if (obj) {
            if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
                obj.select();
                return document.selection.createRange().text;
            }
            else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
                if (obj.files) {
                    return obj.files.item(0).getAsDataURL();
                }
                return obj.value;
            }
            return obj.value;
        }
    }

});
/**
 * 文件选择组件事件适配
 *
 * @author chenxue
 * @create 2016/12/27
 */
$.WebForm.eventAdapter("FileSelect", "selectChange", "FilterSheetSelect", "filter", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/13
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/3/31
 */
$.WebForm.uiRender("FilterDictionarySelect", function($filterDictionarySelect, webForm) {
    var uiName = $filterDictionarySelect.attr("ui-name");

    var readonly = $filterDictionarySelect.attr("readonly");
    if(readonly=="readonly") {
        $filterDictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $filterDictionarySelect.on("change", function() {
        var uiValue = $filterDictionarySelect.val();
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });
    $filterDictionarySelect.on("filter", function(_, filterValue){

        $.ajaxSetup({
            async: false
        });
        var $webForm = JSON.parse(JSON.stringify(webForm.getValues()));
        $webForm.code = filterValue;
        webForm.loadData(uiName, $webForm, function(data){
            var value;
            $filterDictionarySelect.empty();
            if(data.length != 0){
                for(var i in data) {
                    var opt = data[i];
                    if(opt.item==webForm.getValue(uiName)){
                        value = webForm.getValue(uiName);

                    }
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($filterDictionarySelect);
                }
                if(value)
                {
                    webForm.setValue(uiName, value);
                }
                else{
                    webForm.setValue(uiName, data[0].item);
                }

            }

        });

        $.ajaxSetup({
            async: true
        });
        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $filterDictionarySelect.trigger("chosen:updated");

    });

    $filterDictionarySelect.on("refresh", function(_, filterValue){
        $.ajaxSetup({
            async: false
        });
        //
        webForm.loadData(uiName, { code:filterValue, code1:'1'}, function(data){
            var value;
            $filterDictionarySelect.empty();
            if(data.length != 0){
                for(var i in data) {
                    var opt = data[i];
                    if(opt.item==webForm.getValue(uiName)){
                        value = webForm.getValue(uiName);
                    }
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($filterDictionarySelect);
                }
                if(value)
                {
                    webForm.setValue(uiName, value);
                }
                else{
                    webForm.setValue(uiName, data[0].item);
                }
            }
        });
        $.ajaxSetup({
            async: true
        });
        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $filterDictionarySelect.trigger("chosen:updated");
    });

    //
    $filterDictionarySelect.on("value", function(_, value) {
        /*$filterDictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $filterDictionarySelect.val(value);
        var uiLabel = $filterDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $filterDictionarySelect.trigger("chosen:updated");
        /*$filterDictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $filterDictionarySelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $filterDictionarySelect.addClass("chosen-select");
});

/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/3/31
 */

$.WebForm.eventAdapter("FilterDictionarySelect","selectChange","FilterDictionarySelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "IsDisableInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "TradeCommonSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "TextInput", "refresh", function(data){
    return data.value;
});

$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "Text", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "SpecPdtOldOnlySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "MutiSourceDicSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "MutiSourceDicSelect", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "BaseMutiSvcDicSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "SerialnoInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "NotAllMultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "FilterGradeFundSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "DateTimeInput", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "DefaultAllMultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "MultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "FilterNoDftDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterDictionarySelect", "selectChange", "FilterFundCodeSelect", "filter", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/3/31
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/11/17
 */

$.WebForm.uiRender("FilterFundCodeSelect", function($filterFundCodeSelect, webForm) {
    var uiName = $filterFundCodeSelect.attr("ui-name");

    var readonly = $filterFundCodeSelect.attr("readonly");
    if(readonly=="readonly") {
        $filterFundCodeSelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $filterFundCodeSelect.on("change", function() {
        var uiValue = $filterFundCodeSelect.val();
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });
    $filterFundCodeSelect.on("filter", function(_, filterValue){

        $.ajaxSetup({
            async: false
        });

        var  c_managercode= webForm.getValue("c_managercode");
        var  c_tacode= webForm.getValue("c_tacode");
        var  c_department= webForm.getValue("c_department");

        var  c_trusteecode = "";
        var  c_trusteecode_array= webForm.getValue("c_trusteecode");
        if(c_trusteecode_array != null && c_trusteecode_array != "") {
            c_trusteecode = c_trusteecode_array.join(',')
        }
        //
        webForm.loadData(uiName, {c_trusteecode:c_trusteecode, c_managercode:c_managercode, c_tacode:c_tacode,c_department:c_department}, function(data){
            var value;
            $filterFundCodeSelect.empty();
            if(data.length != 0){
                $('<option value=""></option>').appendTo($filterFundCodeSelect);
                for(var i in data) {
                    var opt = data[i];
                    if(opt.item==webForm.getValue(uiName)){
                        value = webForm.getValue(uiName);

                    }
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($filterFundCodeSelect);
                }
                if(value)
                {
                    webForm.setValue(uiName, value);
                }
                /*else{
                 webForm.setValue(uiName, data[0].item);
                 }*/

            }

        });

        $.ajaxSetup({
            async: true
        });
        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $filterFundCodeSelect.trigger("chosen:updated");

    });

    $filterFundCodeSelect.on("refresh", function(_, filterValue){
        $.ajaxSetup({
            async: false
        });
        //
        webForm.loadData(uiName, { code:filterValue, code1:'1'}, function(data){
            var value;
            $filterFundCodeSelect.empty();
            if(data.length != 0){
                $('<option value=""></option>').appendTo($filterFundCodeSelect);
                for(var i in data) {
                    var opt = data[i];
                    if(opt.item==webForm.getValue(uiName)){
                        value = webForm.getValue(uiName);
                    }
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($filterFundCodeSelect);
                }
                if(value)
                {
                    webForm.setValue(uiName, value);
                }
                else{
                    webForm.setValue(uiName, data[0].item);
                }
            }
        });
        $.ajaxSetup({
            async: true
        });
        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $filterFundCodeSelect.trigger("chosen:updated");
    });

    //
    $filterFundCodeSelect.on("value", function(_, value) {
        /*$filterFundCodeSelect.children("option:selected").siblings().attr("disabled", false);*/
        $filterFundCodeSelect.val(value);
        var uiLabel = $filterFundCodeSelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $filterFundCodeSelect.trigger("chosen:updated");
        /*$filterFundCodeSelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $filterFundCodeSelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $filterFundCodeSelect.addClass("chosen-select");
});

/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/11/17
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/11/17
 */
/**
 * TODO description here
 *
 * @author liangll18922
 * @create 2016/12/30
 */
$.WebForm.uiRender("FilterGradeFundSelect", function($filterGradeFundSelect, webForm) {
    var uiName = $filterGradeFundSelect.attr("ui-name");

    var readonly = $filterGradeFundSelect.attr("readonly");
    if(readonly=="readonly") {
        $filterGradeFundSelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $filterGradeFundSelect.on("change", function() {
        var uiValue = $filterGradeFundSelect.val();
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });
    $filterGradeFundSelect.on("filter", function(_, filterValue){

        $.ajaxSetup({
            async: false
        });

        //
        webForm.loadData(uiName, {code:filterValue}, function(data){
            var value;
            $filterGradeFundSelect.empty();
            if(data.length != 0){
                $('<option value=""></option>').appendTo($filterGradeFundSelect);
                for(var i in data) {
                    var opt = data[i];
                    if(opt.item==webForm.getValue(uiName)){
                        value = webForm.getValue(uiName);

                    }
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($filterGradeFundSelect);
                }
                if(value)
                {
                    webForm.setValue(uiName, value);
                }

            }

        });

        $.ajaxSetup({
            async: true
        });
        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $filterGradeFundSelect.trigger("chosen:updated");

    });

    $filterGradeFundSelect.on("refresh", function(_, filterValue){
        $.ajaxSetup({
            async: false
        });
        //
        webForm.loadData(uiName, { code:filterValue, code1:'1'}, function(data){
            var value;
            $filterGradeFundSelect.empty();
            if(data.length != 0){
                for(var i in data) {
                    var opt = data[i];
                    if(opt.item==webForm.getValue(uiName)){
                        value = webForm.getValue(uiName);
                    }
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($filterGradeFundSelect);
                }
                if(value)
                {
                    webForm.setValue(uiName, value);
                }
                else{
                    webForm.setValue(uiName, data[0].item);
                }
            }
        });
        $.ajaxSetup({
            async: true
        });
        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $filterGradeFundSelect.trigger("chosen:updated");
    });

    //
    $filterGradeFundSelect.on("value", function(_, value) {
        /*$filterGradeFundSelect.children("option:selected").siblings().attr("disabled", false);*/
        $filterGradeFundSelect.val(value);
        var uiLabel = $filterGradeFundSelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $filterGradeFundSelect.trigger("chosen:updated");
        /*$filterGradeFundSelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $filterGradeFundSelect.chosen({
        width: "100%", search_contains: true,
        no_results_text: "没有匹配项",
        placeholder_text:"请选择",
        allow_single_deselect: true,//可删除已经选择的项
        disable_search_threshold: 2
    }).change(function(){
        if($filterGradeFundSelect.attr("ui-name")==$filterGradeFundSelect.attr("name")){
            var validator = $(this).parents().find("form").validate();
            validator.element($(this));
        }

    });

    $filterGradeFundSelect.addClass("chosen-select");
});

/**
 * TODO description here
 *
 * @author liangll18922
 * @create 2016/12/30
 */

$.WebForm.eventAdapter("FilterGradeFundSelect","selectChange","FilterDictionarySelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterGradeFundSelect", "selectChange", "IsDisableInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterGradeFundSelect", "selectChange", "TradeCommonSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterGradeFundSelect", "selectChange", "TextInput", "refresh", function(data){
    return data.value;
});

$.WebForm.eventAdapter("FilterGradeFundSelect", "selectChange", "Text", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterGradeFundSelect", "selectChange", "SpecPdtOldOnlySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterGradeFundSelect", "selectChange", "MutiSourceDicSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterGradeFundSelect", "selectChange", "MutiSourceDicSelect", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterGradeFundSelect", "selectChange", "BaseMutiSvcDicSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterGradeFundSelect", "selectChange", "SerialnoInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterGradeFundSelect", "selectChange", "NotAllMultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/3/31
 */
/**
 * TODO description here
 *
 * @author caoyy21009
 * @create 2018/5/15
 */
$.WebForm.uiRender("FilterNoDftDictionarySelect", function($filterNoDftDictionarySelect, webForm) {
    var uiName = $filterNoDftDictionarySelect.attr("ui-name");

    var readonly = $filterNoDftDictionarySelect.attr("readonly");
    if(readonly=="readonly") {
        $filterNoDftDictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $filterNoDftDictionarySelect.on("change", function() {
        var uiValue = $filterNoDftDictionarySelect.val();
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });

    $filterNoDftDictionarySelect.on("filter", function(_, filterValue){
        $.ajaxSetup({
            async: false
        });
        var $webForm = JSON.parse(JSON.stringify(webForm.getValues()));
        $webForm.code = filterValue;

        //
        webForm.loadData(uiName, $webForm, function(data){
            var value;
            $filterNoDftDictionarySelect.empty();
            if(data.length != 0){
                $('<option value=""></option>').appendTo($filterNoDftDictionarySelect);
                for(var i in data) {
                    var opt = data[i];
                    if(opt.item==webForm.getValue(uiName)){
                        value = webForm.getValue(uiName);

                    }
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($filterNoDftDictionarySelect);
                }
                if(value)
                {
                    webForm.setValue(uiName, value);
                }
                else{
                    webForm.setValue(uiName, "");
                }

            } else {
                webForm.setValue(uiName, "");
            }

        });

        $.ajaxSetup({
            async: true
        });
        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $filterNoDftDictionarySelect.trigger("chosen:updated");

    });

    $filterNoDftDictionarySelect.on("refresh", function(_, filterValue){
        $.ajaxSetup({
            async: false
        });
        var $webForm = JSON.parse(JSON.stringify(webForm.getValues()));
        $webForm.code = filterValue;

        webForm.loadData(uiName, $webForm, function(data){
            var value;
            $filterNoDftDictionarySelect.empty();
            if(data.length != 0){
                $('<option value=""></option>').appendTo($filterNoDftDictionarySelect);
                for(var i in data) {
                    var opt = data[i];
                    if(opt.item==webForm.getValue(uiName)){
                        value = webForm.getValue(uiName);
                    }
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($filterNoDftDictionarySelect);
                }
                if(value)
                {
                    webForm.setValue(uiName, value);
                }
                else{
                    webForm.setValue(uiName, "");
                }
            }
        });
        $.ajaxSetup({
            async: true
        });
        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $filterNoDftDictionarySelect.trigger("chosen:updated");
    });

    //
    $filterNoDftDictionarySelect.on("value", function(_, value) {
        /*$filterNoDftDictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $filterNoDftDictionarySelect.val(value);
        var uiLabel = $filterNoDftDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $filterNoDftDictionarySelect.trigger("chosen:updated");
        /*$filterNoDftDictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $filterNoDftDictionarySelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $filterNoDftDictionarySelect.addClass("chosen-select");
});

/**
 * TODO description here
 *
 * @author caoyy21009
 * @create 2018/5/15
 */
$.WebForm.eventAdapter("FilterNoDftDictionarySelect","selectChange","FilterNoDftDictionarySelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterNoDftDictionarySelect","selectChange","NotAllMultipeFilterDictionarySelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterNoDftDictionarySelect", "selectChange", "TextInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterNoDftDictionarySelect", "selectChange", "FilterFundCodeSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterNoDftDictionarySelect", "selectChange", "MultipleDicSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterNoDftDictionarySelect", "selectChange", "FilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterNoDftDictionarySelect", "selectChange", "DefaultAllMultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterNoDftDictionarySelect", "selectChange", "TradeCommonSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FilterNoDftDictionarySelect", "selectChange", "MultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author caoyy21009
 * @create 2018/5/15
 */
/**
 * sheet选择组件 (Select模式)
 *
 * @author gavin
 * @create 2015/11/13
 */
$.WebForm.uiRender("FilterSheetSelect", function($filterSheetSelect, webForm) {
    var uiName = $filterSheetSelect.attr("ui-name");

    var readonly = $filterSheetSelect.attr("readonly");
    if(readonly=="readonly") {
        $filterSheetSelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $filterSheetSelect.on("change", function() {
        var uiValue = $filterSheetSelect.val();
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });

    //
    $filterSheetSelect.on("value", function(_, value) {
        /*$filterSheetSelect.children("option:selected").siblings().attr("disabled", false);*/
        $filterSheetSelect.val(value);
        var uiLabel = $filterSheetSelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $filterSheetSelect.trigger("chosen:updated");
        /*$filterSheetSelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $filterSheetSelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $filterSheetSelect.addClass("chosen-select");
});

/**
 * 字典选择组件事件适配
 *
 * @author gavin
 * @create 2015/11/13
 */


/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/13
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/1/26
 */
/**
 * 文本输入组件
 *
 * @author Gavin Hu
 * @create 2015/11/17
 */
$.WebForm.uiRender("FundAccoInput", function($fundAccoInput, webForm){
    var uiName = $fundAccoInput.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    $fundAccoInput.on("input keypress change", function() {
        //
        var newValue = $fundAccoInput.val();
        if(newValue==uiValue) {
            return; // 未修改
        }
        //
        //
        webForm.setValue(uiName, newValue);
        //
    });
    //失去焦点后触发其他被动事件
    $fundAccoInput.on("blur", function() {
        //
        var newValue = $fundAccoInput.val();
        if(newValue==uiValue) {
            return; // 未修改
        }
        //
        var uiEvent = {
            name: "focusChange",
            data: {
                value: newValue
            }
        };
        //
        webForm.setInternalValue(uiName, uiEvent.data.value);
        webForm.triggerEvent(uiName, uiEvent);
    });
    //失去焦点后触发自身事件
    $fundAccoInput.blur(function() {
        var fundAcco = webForm.getValue(uiName);
        //
        var taCode= webForm.getValue("c_tacode");//
        var showconfigCode = $fundAccoInput.parents('form').attr("showConfigCode");

        webForm.loadData(uiName, {uiName:uiName,c_tacode: taCode, c_fundacco: fundAcco,c_otheracco: fundAcco,showconfigCode:showconfigCode}, function (data) {
            if (data[0].errorNo != 0) {
                //
                if (fundAcco != '') { //基金账号不输入值，仍校验但不给出提示
                    webForm.setValue(uiName, "");
                    layer.msg(data[0].errorMes);
                }
                var newValue = $fundAccoInput.val();
                var uiEvent = {
                    name: "textChange",
                    data: {
                        value: newValue
                    }
                };
                webForm.triggerEvent(uiName, uiEvent);

            }
        });

    });
    //清空自身值并触发焦点失去事件
    $fundAccoInput.on("refresh", function(_, taCode) {
        webForm.setValue(uiName, "");
        var newValue = $fundAccoInput.val();
        var uiEvent = {
            name: "textChange",
            data: {
                value: newValue
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
    });

    //
    $fundAccoInput.on("value", function(_, value) {
        $fundAccoInput.val(value);
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $fundAccoInput.trigger("chosen:updated");
    });
});

/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/1/26
 */
//账户冻结相关适配器start
$.WebForm.eventAdapter("FundAccoInput", "textChange", "TradeCommonSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FundAccoInput", "textChange", "TextInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FundAccoInput", "textChange", "Text", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FundAccoInput", "focusChange", "TradeCommonSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FundAccoInput", "focusChange", "TextInput", "refresh", function(data){
    return data.value;
});
//账户冻结相关适配器end

/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/1/26
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/1/28
 */

$.WebForm.uiRender("FundCodeSelect", function($fundCodeSelect, webForm){
    var uiName = $fundCodeSelect.attr("ui-name");
    var value =  webForm.getValue(uiName);
    var readonly = $fundCodeSelect.attr("readonly");
    //


    $fundCodeSelect.on("filter", function(_, code){
        //

        webForm.loadData(uiName, {code:code}, function(data){
            $fundCodeSelect.empty();
            if(data.length != 0) {
                for (var i in data) {
                    var fund = data[i];
                    //
                    $('<option value="' + fund.item + '">' + fund.caption + '</option>').appendTo($fundCodeSelect);
                }
            }else{
                $('<option></option>').appendTo($fundCodeSelect);
            }
            if(value){
                $fundCodeSelect.val(value);
                webForm.setInternalValue(uiName, value);

            }else if(data.length>0){
                webForm.setInternalValue(uiName, data[0].item);

            }else{
                webForm.setInternalValue(uiName, "");
            }
            var uiValue = $fundCodeSelect.val();
            var uiLabel = $fundCodeSelect.children("option:selected").text();
            var uiEvent = {
                name: "selectChange",
                data: {
                    label: uiLabel,
                    value: uiValue
                }
            };
            if(readonly!="readonly") {
                //
                webForm.triggerEvent(uiName, uiEvent);
            }
            /*otherCode(data);*/
            // $fundCodeSelect.change();
            $fundCodeSelect.trigger("chosen:updated");
        });
    });
    //
    $fundCodeSelect.on("change",function() {
        var uiValue = $fundCodeSelect.val();
        //
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });
    $fundCodeSelect.on("value", function(_, value) {
        //
        $fundCodeSelect.val(value);
        var uiLabel = $fundCodeSelect.children("option:selected").text();
        var uiEvent = {
            name:"selectChange",
            data: {
                label : uiLabel,
                value : value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $fundCodeSelect.trigger("chosen:updated");
    });
});
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/1/28
 */
$.WebForm.eventAdapter("FundCodeSelect", "selectChange", "TradeCommonSelect", "filter", function(data){
    return data.value;
});

$.WebForm.eventAdapter("FundCodeSelect", "selectChange", "TextInput", "refresh", function(data){
    return data.value;
});

$.WebForm.eventAdapter("FundCodeSelect", "selectChange", "Text", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FundCodeSelect", "selectChange", "SerialnoInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FundCodeSelect", "selectChange", "SpecPdtOldOnlySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FundCodeSelect", "selectChange", "FundOpenDayInput", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("FundCodeSelect", "selectChange", "IsDisableInput", "refresh", function(data){
    return data.value;
});

/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/1/28
 */
/**
 * 日期时间输入组件
 *
 * @author Gavin Hu
 * @create 2015/11/17
 */
$.WebForm.uiRender("FundOpenDayInput", function($dateTimeInput, webForm) {
    var uiName = $dateTimeInput.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    //
    var dateChange = function(date){
        var rowid = webForm.getValue("l_rowid");
        var fundcode = webForm.getValue("c_fundcode");
        var begindate = webForm.getValue("d_begindate");
        var enddate = webForm.getValue("d_enddate");
        if(date == ""){
        }else if(fundcode == ""){
            layer.msg("请先选择基金！");
            date = "";
        }else{
            if(uiName == "d_begindate"){
                begindate = date;
                webForm.loadData(uiName, {fundcode:fundcode,begindate:begindate,type:1}, function(result){
                    if(result.err){
                        date = "";
                        layer.msg(result.err_msg);
                        webForm.setValue(uiName,date);
                    }
                 });
            }else{
                enddate = date;
            }
            if(begindate!="" && enddate!=""){
                webForm.loadData(uiName, {rowid:rowid,fundcode:fundcode,begindate:begindate,enddate:enddate,type:2}, function(result){
                    if(result.err){
                        date = "";
                        layer.msg(result.err_msg);
                        webForm.setValue(uiName,date);
                    }
                });
            }
        }
        //
        webForm.setValue(uiName,date);
        //
    };
    //
    $dateTimeInput.on("filter", function() {
        var date = $dateTimeInput.val();
        dateChange(date);
    });
    $dateTimeInput.on("click", function() {
        var uiValue = webForm.getValue(uiName);
        var settings = {
            choose: function(date) {
                //
                if(date==uiValue) {
                    return ; // 未修改
                }
                //
                dateChange(date);
            }
        };
        //
        $.extend(settings, webForm.getMeta(uiName));
        //
        laydate(settings);
        //
        $("#laydate_ok,#laydate_today,#laydate_clear").on("click", function(){
            var date = $dateTimeInput.val();
            //
            dateChange(date);
        });
    });
    //
    $dateTimeInput.on("input", function(){
        //
        dateChange("");
    });
    //
    $dateTimeInput.on("value", function(_, value){
        $dateTimeInput.val(value);
    });
});
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/17
 */
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/17
 */
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/20
 */
$.WebForm.uiRender("HiddenInput", function($hiddenInput, webForm){
    var uiName = $hiddenInput.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }

    $hiddenInput.on("refresh", function(_, value) {
        var c_fundcode=webForm.getValue("c_fundcode");

        webForm.loadData(uiName, {c_fundcode:c_fundcode}, function(data) {
            var uiValue = '';
            if (data != null && data.length != 0){
                uiValue = data[0][uiName];
            }
            webForm.setValue(uiName, uiValue);
        });
    });
    
    
    $hiddenInput.on("value", function(_, value) {
        $hiddenInput.val(value);
    });
});
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/20
 */

$.WebForm.eventAdapter("HiddenInput", "textChange", "NotAllMultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});

$.WebForm.eventAdapter("HiddenInput", "textChange", "MultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("HiddenInput", "textChange", "FilterDictionarySelect", "filter", function(data){
    return data.value;
});

/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/20
 */
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/3/28
 */
$.WebForm.uiRender("IsDisableInput", function($isDisableInput, webForm){
    var uiName = $isDisableInput.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    var readonly = $isDisableInput.attr("readonly");
    //
    $isDisableInput.on("refresh", function(_, value) {

        var c_fundcode=webForm.getValue("c_fundcode");
        var d_cdate=webForm.getValue("d_cdate");
        var c_fundendmode=webForm.getValue("c_fundendmode");
        var c_needcalallotbalance=webForm.getValue("c_needcalallotbalance");
        var c_fundendtype=webForm.getValue("c_fundendtype");

        webForm.loadData(uiName, {c_fundcode:c_fundcode,
            d_cdate:d_cdate}, function(data) {
            //TA柜台份额调整：调整收益
            if(uiName == 'f_income' ){
                if (data != null && data.length != 0){
                    if(data[0]["c_investorientation"]==2){
                        webForm.setValue(uiName, '');
                        $isDisableInput.attr("disabled", false);
                        $isDisableInput.parents("dd").prev().find(".red_icon").show();
                    }else{
                        webForm.setValue(uiName, '0.00');
                        $isDisableInput.attr("disabled",true);
                        $isDisableInput.parents("dd").prev().find(".red_icon").hide();
                    }
                }
            }
        });
    });

    //
    $isDisableInput.on("input keypress", function() {
        var uiValue = webForm.getValue(uiName);

        var newValue = $isDisableInput.val();
        if(newValue==uiValue) {
            return; // 未修改
        }
        webForm.setValue(uiName, newValue);
    });

    //
    $isDisableInput.on("value", function(_, value) {
        $isDisableInput.val(value);
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $isDisableInput.trigger("chosen:updated");
    });
});
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/3/28
 */
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/3/28
 */
/**
 * 日期时间输入组件
 *
 * @author Gavin Hu
 * @create 2015/11/17
 */
$.WebForm.uiRender("MonthInput", function($monthInput, webForm) {
    var uiName = $monthInput.attr("ui-name");
    //
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue.split('-').join(''));
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    var dateChange = function(date){
        //
        webForm.setValue(uiName, date.split('-').join(''));
        //
    };
    var setValues = function(webForm,uiName, uiValue){
        webForm.context.uiValues[uiName] = uiValue;
        //
        var $ui = webForm.context.uiObjects[uiName];
        //
        $ui.trigger("value", [uiValue]);
    }
    //
    $monthInput.on("click", function() {
        var uiValue = webForm.getValue(uiName);
        var settings = {
            choose: function(date) {
                //
                if(date==uiValue) {
                    return ; // 未修改
                }
                //
                dateChange(date);
            },
            format:'YYYY-MM',
            onlyYM:true,
            istoday:false
        };
        //
        $.extend(settings, webForm.getMeta(uiName));
        //
        laydate(settings);
        //
        $("#laydate_ok,#laydate_today,#laydate_clear").on("click", function(){
            var date = $monthInput.val();
            //
            dateChange(date);
        });
    });
    $monthInput.on("filter", function(_, tacode){
        $.ajaxSetup({
            async: false
        });
        //
        var a=tacode;
        if(tacode) {
            webForm.loadData(uiName, {tacode:tacode}, function(data){
                if(data.length>0){
                    $monthInput.val(data[0].item);
                    dateChange(data[0].item);
                }
            });
        }
        $.ajaxSetup({
            async: true
        });

    });
    //
    $monthInput.on("input", function(){
        /*var uiName = $monthInput.attr("ui-name");
         webForm.setInternalValue(uiName, "");
         setValues(webForm,uiName,"");*/
        var date = $monthInput.val();
        //
        dateChange(date);
    });

    //
    $monthInput.on("value", function(_, value){
        if(value.length == 6){
            value = value.substring(0, 4) + "-" + value.substring(4, 6);
        }
        $monthInput.val(value);
        var uiEvent = {
            name: "dateChange",
            data: {
                value: value.split('-').join('')
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $monthInput.trigger("chosen:updated");
    });

    $monthInput.on("dateChange",function(){
        var thisForm=$(this).parents().find("form");
        var validator = thisForm.validate();
        validator.element($(this));
    });
});
/**
 * TODO description here
 *
 * @author xujh13699
 * @create 2015/12/18
 */
/**
 * TODO description here
 *
 * @author xujh13699
 * @create 2015/12/18
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/4/12
 */

$.WebForm.uiRender("MultipeDictionarySelect", function($multipeDictionarySelect, webForm) {
    var uiName = $multipeDictionarySelect.attr("ui-name");
    var readonly = $multipeDictionarySelect.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $multipeDictionarySelect.find("option:first").val());
    }
    if(readonly=="readonly") {
        $multipeDictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $multipeDictionarySelect.on("change", function() {
        var uiValue = $multipeDictionarySelect.val();
        if(uiValue==null){
            uiValue="";
        }

        if(readonly!="readonly") {
            webForm.setValue(uiName,uiValue);
            //
        }
    });
    $multipeDictionarySelect.on("filter", function() {
        var uiValue = $multipeDictionarySelect.val();
        if(uiValue==null){
            uiValue="";
        }
        var uiLabel = $multipeDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: uiValue
            }
        };
        if(readonly!="readonly") {
            webForm.setInternalValue(uiName, uiEvent.data.value);
            //
            webForm.triggerEvent(uiName, uiEvent);
        }
    });
    //
    $multipeDictionarySelect.on("value", function(_, value) {
        /*$multipeDictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $multipeDictionarySelect.val(value);
        var uiLabel = $multipeDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $multipeDictionarySelect.trigger("chosen:updated");
        /*$multipeDictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });


    $multipeDictionarySelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $multipeDictionarySelect.addClass("chosen-select");
});

/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/4/12
 */
$.WebForm.eventAdapter("MultipeDictionarySelect", "selectChange", "FilterFundCodeSelect", "filter",function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/4/12
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/4/19
 */

$.WebForm.uiRender("MultipeFilterDictionarySelect", function($multipeFilterDictionarySelect, webForm) {
    var uiName = $multipeFilterDictionarySelect.attr("ui-name");
    var readonly = $multipeFilterDictionarySelect.attr("readonly");
    if(readonly=="readonly") {
        $multipeFilterDictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $multipeFilterDictionarySelect.on("change", function() {
        var uiValue = $multipeFilterDictionarySelect.val();
        if(uiValue==null){
            uiValue="";
        }
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });
    $multipeFilterDictionarySelect.on("filter", function(_, filterValue){

        //
        if($multipeFilterDictionarySelect.attr("disabled")){
            return;
        }

        var $webForm = $.extend({}, webForm.getValues());
        $webForm.code=filterValue;

        webForm.loadData(uiName, $webForm, function(data){
            $multipeFilterDictionarySelect.empty();
            if(data.length != 0){
                for(var i in data) {
                    var opt = data[i];
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($multipeFilterDictionarySelect);
                }
                webForm.setValue(uiName, '');
            }

            $multipeFilterDictionarySelect.trigger("chosen:updated");
            /*var validator =$multipeFilterDictionarySelect.parents().find("form").validate();
             validator.element($multipeFilterDictionarySelect);*/

        });
    });
    //
    $multipeFilterDictionarySelect.on("value", function(_, value) {
        /*$multipeFilterDictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $multipeFilterDictionarySelect.val(value);
        var uiLabel = $multipeFilterDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $multipeFilterDictionarySelect.trigger("chosen:updated");
        /*$multipeFilterDictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $multipeFilterDictionarySelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $multipeFilterDictionarySelect.addClass("chosen-select");
});

/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/4/19
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/4/19
 */
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2017/3/21
 */

$.WebForm.uiRender("MultipeStringDictionarySelect", function($multipeStringDictionarySelect, webForm) {
    var uiName = $multipeStringDictionarySelect.attr("ui-name");
    var readonly = $multipeStringDictionarySelect.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $multipeStringDictionarySelect.find("option:first").val());
    }
    if(readonly=="readonly") {
        $multipeStringDictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $multipeStringDictionarySelect.on("change", function() {
        var uiValue = $multipeStringDictionarySelect.val();
        if(uiValue==null){
            uiValue="";
        }else{
            uiValue=uiValue.join(",");
        }
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });
    $multipeStringDictionarySelect.on("filter", function() {
        var uiValue = $multipeStringDictionarySelect.val();
        if(uiValue==null){
            uiValue="";
        }
        var uiLabel = $multipeStringDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: uiValue
            }
        };
        if(readonly!="readonly") {
            webForm.setInternalValue(uiName, uiEvent.data.value);
            //
            webForm.triggerEvent(uiName, uiEvent);
        }
    });
    //
    $multipeStringDictionarySelect.on("value", function(_, value) {
        value=value.split(",");
        $multipeStringDictionarySelect.val(value);
        var uiLabel = $multipeStringDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $multipeStringDictionarySelect.trigger("chosen:updated");

    });


    $multipeStringDictionarySelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $multipeStringDictionarySelect.addClass("chosen-select");
});

/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2017/3/21
 */
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2017/3/21
 */
/**
 * TODO description here
 *
 * @author zhengxiao19642
 * @create 2017/3/23
 */
$.WebForm.uiRender("MultipeStringFilterDictionarySelect", function($multipeStringFilterDictionarySelect, webForm) {
    var uiName = $multipeStringFilterDictionarySelect.attr("ui-name");
    var readonly = $multipeStringFilterDictionarySelect.attr("readonly");
    if(readonly=="readonly") {
        $multipeStringFilterDictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $multipeStringFilterDictionarySelect.on("change", function() {
        var uiValue = $multipeStringFilterDictionarySelect.val();
        if (uiValue == null) {
            uiValue="";
        } else {
            uiValue=uiValue.join(",");
        }
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
        }
    });

    $multipeStringFilterDictionarySelect.on("filter", function(_, filterValue){
        $.ajaxSetup({
            async: false
        });

        webForm.loadData(uiName, {code:filterValue}, function(data){
            $multipeStringFilterDictionarySelect.empty();

            var value = webForm.getValue(uiName);
            var newValue = new Array();
            if(data.length != 0){
                if (value == null || value == '') {
                    for(var i in data) {
                        var opt = data[i];
                        $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($multipeStringFilterDictionarySelect);
                    }
                } else {
                    value = value.split(",");
                    for(var i in data) {
                        var opt = data[i];
                        if (value == null || $.inArray(opt.item, value) == -1 ) {
                            $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($multipeStringFilterDictionarySelect);
                        } else {
                            $('<option value="'+opt.item+'" selected = "selected">'+ opt.caption +'</option>').appendTo($multipeStringFilterDictionarySelect);
                            value.splice($.inArray(opt.item, value),1);
                            newValue.push(opt.item);
                        }
                    }
                    webForm.setValue(uiName, newValue.join(","));
                }
            } else {
                webForm.setValue(uiName, '');
            }
            $multipeStringFilterDictionarySelect.trigger("chosen:updated");
        });

        $.ajaxSetup({
            async: true
        });

        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $multipeStringFilterDictionarySelect.trigger("chosen:updated");
    });

    //
    $multipeStringFilterDictionarySelect.on("value", function(_, value) {
        if (value != null) {
            value = value.split(",");
        }
        $multipeStringFilterDictionarySelect.val(value);
        var uiLabel = $multipeStringFilterDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $multipeStringFilterDictionarySelect.trigger("chosen:updated");
    });

    $multipeStringFilterDictionarySelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $multipeStringFilterDictionarySelect.addClass("chosen-select");
});
/**
 * TODO description here
 *
 * @author zhengxiao19642
 * @create 2017/3/23
 */
/**
 * TODO description here
 *
 * @author zhengxiao19642
 * @create 2017/3/23
 */
/**
 * TODO description here
 *
 * @author luobiao
 * @create 2018/1/8
 */
$.WebForm.uiRender("MultipleDicSelect", function($multipleDicSelect, webForm) {
    var uiName = $multipleDicSelect.attr("ui-name");

    var readonly = $multipleDicSelect.attr("readonly");
    if(readonly=="readonly") {
        $multipleDicSelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $multipleDicSelect.on("change", function() {
        var uiValue = $multipleDicSelect.val();
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });
    $multipleDicSelect.on("filter", function(_, filterValue){

        $.ajaxSetup({
            async: false
        });
        var $webForm = JSON.parse(JSON.stringify(webForm.getValues()));
        $webForm.code=filterValue;
        //
        webForm.loadData(uiName, $webForm, function(data){
            var value;
            $multipleDicSelect.empty();
            if(data.length != 0){
                for(var i in data) {
                    var opt = data[i];
                    if(opt.item==webForm.getValue(uiName)){
                        value = webForm.getValue(uiName);

                    }
                    if(!!opt.caption){
                        $('<option value="'+opt.item+'">'+opt.item+':'+ opt.caption +'</option>').appendTo($multipleDicSelect);
                    }else{
                        $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($multipleDicSelect);
                    }

                }
                if(value)
                {
                    webForm.setValue(uiName, value);
                }
                else{
                    webForm.setValue(uiName, '');
                }

            }

        });

        $.ajaxSetup({
            async: true
        });
        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $multipleDicSelect.trigger("chosen:updated");

    });

    $multipleDicSelect.on("refresh", function(_, filterValue){
        $.ajaxSetup({
            async: false
        });
        //
        webForm.loadData(uiName, { code:filterValue, code1:'1'}, function(data){
            var value;
            $multipleDicSelect.empty();
            if(data.length != 0){
                for(var i in data) {
                    var opt = data[i];
                    if(opt.item==webForm.getValue(uiName)){
                        value = webForm.getValue(uiName);
                    }
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($multipleDicSelect);
                }
                if(value)
                {
                    webForm.setValue(uiName, value);
                }
                else{
                    webForm.setValue(uiName, data[0].item);
                }
            }
        });
        $.ajaxSetup({
            async: true
        });
        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $multipleDicSelect.trigger("chosen:updated");
    });

    //
    $multipleDicSelect.on("value", function(_, value) {
        /*$multipleDicSelect.children("option:selected").siblings().attr("disabled", false);*/
        $multipleDicSelect.val(value);
        var uiLabel = $multipleDicSelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $multipleDicSelect.trigger("chosen:updated");
        /*$multipleDicSelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $multipleDicSelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $multipleDicSelect.addClass("chosen-select");
});

/**
 * TODO description here
 *
 * @author luobiao
 * @create 2018/1/8
 */

$.WebForm.eventAdapter("MultipleDicSelect","selectChange","MultipleDicSelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("MultipleDicSelect", "selectChange", "IsDisableInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("MultipleDicSelect", "selectChange", "TradeCommonSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("MultipleDicSelect", "selectChange", "TextInput", "refresh", function(data){
    return data.value;
});

$.WebForm.eventAdapter("MultipleDicSelect", "selectChange", "Text", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("MultipleDicSelect", "selectChange", "SpecPdtOldOnlySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("MultipleDicSelect", "selectChange", "MutiSourceDicSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("MultipleDicSelect", "selectChange", "MutiSourceDicSelect", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("MultipleDicSelect", "selectChange", "BaseMutiSvcDicSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("MultipleDicSelect", "selectChange", "SerialnoInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("MultipleDicSelect", "selectChange", "NotAllMultipeMultipleDicSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("MultipleDicSelect", "selectChange", "FilterGradeFundSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("MultipleDicSelect", "selectChange", "NotAllMultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("MultipleDicSelect", "selectChange", "FilterDictionarySelect", "filter", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author luobiao
 * @create 2018/1/8
 */
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2016/5/10
 */
$.WebForm.uiRender("MutiSourceDicSelect", function($mutiSourceDicSelect, webForm) {
    var uiName = $mutiSourceDicSelect.attr("ui-name");

    var readonly = $mutiSourceDicSelect.attr("readonly");
    if(readonly=="readonly") {
        $mutiSourceDicSelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $mutiSourceDicSelect.on("change", function() {
        var uiValue = $mutiSourceDicSelect.val();
        if(uiValue==null){
            uiValue="";
        }

        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });
    $mutiSourceDicSelect.on("filter", function(_, filterValue){


        $.ajaxSetup({
            async: false
        });
        //
        webForm.loadData(uiName, { filterFlag:0,code:filterValue}, function(data){
            var value;
            $mutiSourceDicSelect.find('option.filter').remove();
            if(data.length != 0){
                for(var i in data) {
                    var opt = data[i];
                    $('<option value="'+opt.item+'" class="filter">'+ opt.caption +'</option>').appendTo($mutiSourceDicSelect);
                }
                $mutiSourceDicSelect.find('option').each(function(){
                    if($(this).val() == webForm.getValue(uiName)){
                        value = $(this).val();
                    }
                });
                if(value)
                {
                    webForm.setValue(uiName, value);
                }
                else{
                    webForm.setValue(uiName, data[0].item);
                }

            }

        });

        $.ajaxSetup({
            async: true
        });
        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $mutiSourceDicSelect.trigger("chosen:updated");
    });

    $mutiSourceDicSelect.on("refresh", function(_, filterValue){


        $.ajaxSetup({
            async: false
        });
        //
        webForm.loadData(uiName, { filterFlag:1,code:filterValue}, function(data){
            var value;
            $mutiSourceDicSelect.find('option.refresh').remove();
            if(data.length != 0){
                for(var i in data) {
                    var opt = data[i];
                    $('<option value="'+opt.item+'" class="refresh">'+ opt.caption +'</option>').appendTo($mutiSourceDicSelect);
                }
                $mutiSourceDicSelect.find('option').each(function(){
                    if($(this).val() == webForm.getValue(uiName)){
                        value = $(this).val();
                    }
                });
                if(value)
                {
                    webForm.setValue(uiName, value);
                }
                else{
                    webForm.setValue(uiName, data[0].item);
                }

            }

        });

        $.ajaxSetup({
            async: true
        });
        var uiEvent = {
            name: "selectChange",
            data: {
                value: webForm.getValue(uiName)
            }
        };
        webForm.triggerEvent(uiName,uiEvent);
        $mutiSourceDicSelect.trigger("chosen:updated");
    });
    //
    $mutiSourceDicSelect.on("value", function(_, value) {
        /*$mutiSourceDicSelect.children("option:selected").siblings().attr("disabled", false);*/
        $mutiSourceDicSelect.val(value);
        var uiLabel = $mutiSourceDicSelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $mutiSourceDicSelect.trigger("chosen:updated");
        /*$mutiSourceDicSelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $mutiSourceDicSelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $mutiSourceDicSelect.addClass("chosen-select");
});
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2016/5/10
 */
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2016/5/10
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/4/6
 */
$.WebForm.uiRender("NavPublishDay", function($navPublishDay, webForm) {
    var isFrist=true;
    var uiName = $navPublishDay.attr("ui-name");
    var readonly = $navPublishDay.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $navPublishDay.find("option:first").val());
    }
    if(readonly=="readonly") {
        $navPublishDay.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $navPublishDay.on("change", function() {
        var uiValue = $navPublishDay.val();
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });

    $navPublishDay.on("filter", function(_, value) {
        if('0'==value||'3'==value)
        {
            $navPublishDay.empty();
            webForm.setValue(uiName,"");
            $navPublishDay.attr("disabled",true);

        }
        else if(value=='1'||value=='2'){
            $navPublishDay.attr("disabled",false);
            var dictName="";
            if(value=='1')
            {
                dictName='C20190';
            }
            else if(value=='2'){
                dictName='C20191';
            }
            webForm.loadData(uiName,{code:dictName},function(data){
                var uiValue;
                if(isFrist)
                {
                    uiValue=webForm.getValue(uiName);
                    isFrist=false;
                }
                $navPublishDay.empty();
                if(data.length != 0){
                    for(var i in data) {
                        var opt = data[i];
                        $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($navPublishDay);
                    }
                }
                if(uiValue)
                {
                    webForm.setValue(uiName,uiValue);
                }
                else if(data){
                    webForm.setInternalValue(uiName, data[0].item);
                }

                $navPublishDay.trigger("chosen:updated");
            });
        }
    });

    //
    $navPublishDay.on("value", function(_, value) {
        $navPublishDay.val(value);
        webForm.setInternalValue(uiName, value);
        var uiLabel = $navPublishDay.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $navPublishDay.trigger("chosen:updated");
    });
    $navPublishDay.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $navPublishDay.addClass("chosen-select");
});


/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/4/6
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/4/6
 */
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/4/25
 */

$.WebForm.uiRender("NetValueIncomeInput", function ($netValueIncomeInput, webForm) {
    var uiName = $netValueIncomeInput.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    var readonly = $netValueIncomeInput.attr("readonly");
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    //
    $netValueIncomeInput.on("refresh", function (_, value) {

        var c_fundcode = webForm.getValue("c_fundcode");
        var d_cdate = webForm.getValue("d_cdate");
        var f_lasttotalshare = webForm.getValue("f_lasttotalshare");
        var f_fundtotalincome = webForm.getValue("f_fundtotalincome");
        var f_incomeunit = webForm.getValue("f_incomeunit");
        var f_incomeratio = webForm.getValue("f_incomeratio");

        webForm.loadData(uiName, {
            c_fundcode: c_fundcode,
            d_cdate: d_cdate,
            f_fundtotalincome: Number(f_fundtotalincome),
            f_lasttotalshare: Number(f_lasttotalshare),
            f_incomeunit: Number(f_incomeunit),
            f_incomeratio: Number(f_incomeratio)
        }, function (data) {

            //行情录入新增页面
            //1.基金收益
            if (uiName == 'f_fundtotalincome') {
                if (data != null && data.length != 0) {
                    if (data[0]["c_investorientation"] == 2) {
                        webForm.setValue(uiName, f_fundtotalincome);
                        $netValueIncomeInput.attr("disabled", false);
                        $netValueIncomeInput.parents("dd").prev().find("font").show();
                    } else {
                        webForm.setValue(uiName, '0.00');
                        $netValueIncomeInput.attr("disabled", true);
                        $netValueIncomeInput.parents("dd").prev().find("font").hide();
                    }
                }
            }
            //2.万份基金收益
            if (uiName == 'f_incomeunit') {
                if (data != null && data.length != 0) {
                    if (data[0]["c_investorientation"] == 2) {
                        webForm.setValue(uiName, Number(data[0]["f_incomeunit"]));
                        $netValueIncomeInput.attr("disabled", false);
                        $netValueIncomeInput.parents("dd").prev().find("font").show();
                    } else {
                        webForm.setValue(uiName, '0.00000');
                        $netValueIncomeInput.attr("disabled", true);
                        $netValueIncomeInput.parents("dd").prev().find("font").hide();
                    }
                }
            }
            //3.年收益率
            if (uiName == 'f_incomeratio') {
                if (data != null && data.length != 0) {
                    if (data[0]["c_investorientation"] == 2) {
                        webForm.setValue(uiName, Number(data[0]["f_incomeratio"]).toFixed(6));
                        $netValueIncomeInput.attr("disabled", false);
                        $netValueIncomeInput.parents("dd").prev().find("font").show();
                    } else {
                        webForm.setValue(uiName, '0.000000');
                        $netValueIncomeInput.attr("disabled", true);
                        $netValueIncomeInput.parents("dd").prev().find("font").hide();
                    }
                }
            }

        });
    });

    //
    $netValueIncomeInput.on("input keypress", function () {
        var uiValue = webForm.getValue(uiName);

        var newValue = $netValueIncomeInput.val();
        if (newValue == uiValue) {
            return; // 未修改
        }
        webForm.setValue(uiName, newValue);
    });

    //
    $netValueIncomeInput.on("value", function (_, value) {
        $netValueIncomeInput.val(value);
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $netValueIncomeInput.trigger("chosen:updated");
    });
});

/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/4/25
 */
$.WebForm.eventAdapter("NetValueIncomeInput", "textChange", "NetValueIncomeInput", "refresh", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/4/25
 */

/**
 * TODO description here
 *
 * @author xiaoya
 * @create 2015/12/18
 */
$.WebForm.uiRender("NoBlockSelect", function($noBlockSelect, webForm) {
    var uiName = $noBlockSelect.attr("ui-name");
    var readonly = $noBlockSelect.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(readonly=="readonly") {
        $noBlockSelect.children("option:selected").siblings().attr("disabled", true);
    }
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $noBlockSelect.find("option:first").val());
    }
    //
    $noBlockSelect.on("change", function() {
        var uiValue = $noBlockSelect.val();
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });

    //
    $noBlockSelect.on("value", function(_, value) {
        $noBlockSelect.val(value);
        var uiLabel = $noBlockSelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $noBlockSelect.trigger("chosen:updated");
    });

    $noBlockSelect.chosen({
        search_contains: true,
        no_results_text: "没有匹配项",
        placeholder_text:"请选择",
        allow_single_deselect: true,//可删除已经选择的项
        disable_search_threshold: 2,
    }).change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $noBlockSelect.addClass("chosen-select");
});


/**
 * TODO description here
 *
 * @author chenxue
 * @create 2015/12/3
 */
$.WebForm.eventAdapter("NoBlockSelect", "selectChange", "DateTimeInput", "filter", function(data){
    return data.value;
});

$.WebForm.eventAdapter("NoBlockSelect", "selectChange", "DateTimeSectionInput", "filter", function(data){
    return data.value;
});

$.WebForm.eventAdapter("NoBlockSelect", "selectChange", "FundCodeSelect", "filter", function(data){
    return data.value;
});

//账户冻结
$.WebForm.eventAdapter("NoBlockSelect","selectChange","FundAccoInput","refresh",function(data){
    return data.value;
});
$.WebForm.eventAdapter("NoBlockSelect","selectChange","TradeCommonSelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("NoBlockSelect","selectChange","TextInput","refresh",function(data){
    return data.value;
});

$.WebForm.eventAdapter("NoBlockSelect","selectChange","NavPublishDay","filter",function(data){
    return data.value;
});

$.WebForm.eventAdapter("NoBlockSelect", "selectChange", "YmdTimeInput", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("NoBlockSelect", "selectChange", "FilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("NoBlockSelect", "selectChange", "FilterDictionarySelect", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("NoBlockSelect", "selectChange", "MultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("NoBlockSelect", "selectChange", "NotAllMultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("NoBlockSelect", "selectChange", "BaseMutiSvcDicSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("NoBlockSelect", "selectChange", "MultipeSvcDicSelect", "filter", function(data){
    return data.value;
});

$.WebForm.eventAdapter("NoBlockSelect", "selectChange", "DefaultAllMultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});


$.WebForm.eventAdapter("NoBlockSelect","selectChange","FilterFundCodeSelect","filter",function(data){
    return data.value;
});

$.WebForm.eventAdapter("NoBlockSelect","selectChange","MultipeStringFilterDictionarySelect","filter",function(data){
    return data.value;
});

$.WebForm.eventAdapter("NoBlockSelect","selectChange","FilterGradeFundSelect","filter",function(data){
    return data.value;
});

$.WebForm.eventAdapter("NoBlockSelect","selectChange","MonthInput","filter",function(data){
    return data.value;
});

$.WebForm.eventAdapter("NoBlockSelect","selectChange","YearInput","filter",function(data){
    return data.value;
});

$.WebForm.eventAdapter("NoBlockSelect","selectChange","MultipleDicSelect","filter",function(data){
    return data.value;
});

$.WebForm.eventAdapter("NoBlockSelect", "selectChange", "FilterNoDftDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("NoBlockSelect", "selectChange", "FilterNoDftDictionarySelect", "refresh", function(data){
    return data.value;
});$.WebForm.eventAdapter("NoBlockSelect","selectChange","EditableDicSelect","refresh",function(data){
    return data.value;
});
$.WebForm.eventAdapter("NoBlockSelect","selectChange","EditableDicSelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("NoBlockSelect","selectChange","DictionarySelect","filter",function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author chenxue
 * @create 2015/12/3
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/10/11
 */

$.WebForm.uiRender("NotAllMultipeDictionarySelect", function($notAllMultipeDictionarySelect, webForm) {
    var uiName = $notAllMultipeDictionarySelect.attr("ui-name");
    var readonly = $notAllMultipeDictionarySelect.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $notAllMultipeDictionarySelect.find("option:first").val());
    }
    if(readonly=="readonly") {
        $notAllMultipeDictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $notAllMultipeDictionarySelect.on("change", function() {
        var uiValue = $notAllMultipeDictionarySelect.val();
        if(uiValue==null){
            uiValue="";
        }
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });
    $notAllMultipeDictionarySelect.on("filter", function() {
        var uiValue = $notAllMultipeDictionarySelect.val();
        if(uiValue==null){
            uiValue="";
        }
        var uiLabel = $notAllMultipeDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: uiValue
            }
        };
        if(readonly!="readonly") {
            webForm.setInternalValue(uiName, uiEvent.data.value);
            //
            webForm.triggerEvent(uiName, uiEvent);
        }
    });
    //
    $notAllMultipeDictionarySelect.on("value", function(_, value) {
        /*$notAllMultipeDictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $notAllMultipeDictionarySelect.val(value);
        var uiLabel = $notAllMultipeDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $notAllMultipeDictionarySelect.trigger("chosen:updated");
        /*$notAllMultipeDictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });


    $notAllMultipeDictionarySelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $notAllMultipeDictionarySelect.addClass("chosen-select");
});

/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/10/11
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/10/11
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/10/11
 */

$.WebForm.uiRender("NotAllMultipeFilterDictionarySelect", function($notAllMultipeFilterDictionarySelect, webForm) {
    var uiName = $notAllMultipeFilterDictionarySelect.attr("ui-name");
    var readonly = $notAllMultipeFilterDictionarySelect.attr("readonly");
    if(readonly=="readonly") {
        $notAllMultipeFilterDictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $notAllMultipeFilterDictionarySelect.on("change", function() {
        var uiValue = $notAllMultipeFilterDictionarySelect.val();
        if(uiValue==null){
            uiValue="";
        }

        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //

        }
    });
    $notAllMultipeFilterDictionarySelect.on("filter", function(_, filterValue){

        //
        if($notAllMultipeFilterDictionarySelect.attr("disabled")){
            return;
        }

        var $webForm = $.extend({}, webForm.getValues());
        $webForm.code=filterValue;
        //
        webForm.loadData(uiName, $webForm, function(data){
            $notAllMultipeFilterDictionarySelect.empty();
            if(data.length != 0){
                for(var i in data) {
                    var opt = data[i];
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($notAllMultipeFilterDictionarySelect);
                }
                webForm.setValue(uiName, '');
            }

            $notAllMultipeFilterDictionarySelect.trigger("chosen:updated");
            /*var validator =$notAllMultipeFilterDictionarySelect.parents().find("form").validate();
             validator.element($notAllMultipeFilterDictionarySelect);*/

        });
    });
    //
    $notAllMultipeFilterDictionarySelect.on("value", function(_, value) {
        /*$notAllMultipeFilterDictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $notAllMultipeFilterDictionarySelect.val(value);
        var uiLabel = $notAllMultipeFilterDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $notAllMultipeFilterDictionarySelect.trigger("chosen:updated");
        /*$notAllMultipeFilterDictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $notAllMultipeFilterDictionarySelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $notAllMultipeFilterDictionarySelect.addClass("chosen-select");
});

/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/10/11
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/10/11
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/3/22
 */
$.WebForm.uiRender("OrdinaryDictionarySelect", function($ordinaryDictionarySelect, webForm) {
    var uiName = $ordinaryDictionarySelect.attr("ui-name");
    var readonly = $ordinaryDictionarySelect.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $ordinaryDictionarySelect.find("option:first").val());
    }
    if(readonly=="readonly") {
        $ordinaryDictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $ordinaryDictionarySelect.on("change", function() {
        var uiValue = $ordinaryDictionarySelect.val();
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
    });
    $ordinaryDictionarySelect.on("filter", function() {
        var uiValue = $ordinaryDictionarySelect.val();
        var uiLabel = $ordinaryDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: uiValue
            }
        };
        if(readonly!="readonly") {
            webForm.setInternalValue(uiName, uiEvent.data.value);
            //
            webForm.triggerEvent(uiName, uiEvent);
        }
    });
    //
    $ordinaryDictionarySelect.on("value", function(_, value) {
        /*$ordinaryDictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $ordinaryDictionarySelect.val(value);
        var uiLabel = $ordinaryDictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $ordinaryDictionarySelect.trigger("chosen:updated");
        /*$ordinaryDictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $ordinaryDictionarySelect.chosen().change(function(){
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });

    $ordinaryDictionarySelect.addClass("chosen-select");
});

/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/3/22
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/3/22
 */
/**
 * 密码输入控件
 * @author wangfeng13949
 * @create 2015/11/24
 */
$.WebForm.uiRender("PasswordInput", function($passwordInput, webForm) {
    var uiName = $passwordInput.attr("ui-name");
    //
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    $passwordInput.on("input keypress", function() {
        var uiValue = webForm.getValue(uiName);
        //
        var newValue = $passwordInput.val();
        if(newValue==uiValue) {
            return; // 未修改
        }
        //
        //
        webForm.setValue(uiName, newValue);
        //
    });
    $passwordInput.on("value", function(_, value){
        $passwordInput.val(value);
        var uiEvent = {
            name: "passwordChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $passwordInput.trigger("chosen:updated");
    });
});
/**
 * TODO description here
 *
 * @author wangfeng13949
 * @create 2015/11/24
 */
/**
 * TODO description here

 * @author wangfeng13949
 * @create 2015/11/24
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/11/3
 */


$.WebForm.uiRender("PercentageTextInput", function ($percentageTextInput, webForm) {
    var uiName = $percentageTextInput.attr("ui-name");
    var value = $percentageTextInput.val();
    if(!!value){
        webForm.setInternalValue(uiName, value/100);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    //
    $percentageTextInput.on("refresh", function (_, value) {
        webForm.loadData(uiName, {
            code: value
        }, function (data) {
            var uiValue = '';
            if (data != null && data.length != 0) {
                uiValue = data[0][uiName];
            }
            webForm.setValue(uiName, uiValue);
        });
    });

    //
    $percentageTextInput.on("input keypress", function () {
        var uiValue = webForm.getValue(uiName);

        var newValue = $percentageTextInput.val();
        if ((newValue/100) == uiValue) {
            return; // 未修改
        }

        webForm.setValue(uiName, newValue/100);

    });



    //
    $percentageTextInput.on("value", function (_, value) {
        if(!!value) {
            $percentageTextInput.val(value * 100);
        }else{
            $percentageTextInput.val("");
        }
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);

    });

});
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/11/3
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/11/3
 */
/**
 * 申请单号输入组件
 *
 * @author chenxue
 * @create 2016/2/28
 */
$.WebForm.uiRender("RequestNoInput", function($requestNoInput, webForm){
    var uiName = $requestNoInput.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    $requestNoInput.on("input keypress change", function() {
        //
        var newValue = $requestNoInput.val();
        if(newValue==uiValue) {
            return; // 未修改
        }
        //

        //
        webForm.setValue(uiName, newValue);
        //
    });

    $requestNoInput.on("blur", function() {

        //
        var newValue = $requestNoInput.val();
        if(newValue==uiValue) {
            return; // 未修改
        }
        //
        var uiEvent = {
            name: "focusChange",
            data: {
                value: newValue
            }
        };
        //
        webForm.setInternalValue(uiName, uiEvent.data.value);
        webForm.triggerEvent(uiName, uiEvent);
    });

    $requestNoInput.blur(function() {
        var c_orirequestno = webForm.getValue(uiName);
        //
        var c_tacode = webForm.getValue("c_tacode");

        if (c_orirequestno != '') {
            webForm.loadData(uiName, {c_tacode: c_tacode, c_orirequestno: c_orirequestno}, function (data) {
                if (data[0].errorNo != 0) {
                    layer.msg(data[0].errorMes);
                    webForm.setValue(uiName,'');
                }
            });
        }
        //
    });

    $requestNoInput.on("refresh", function(_, taCode) {
        webForm.setValue(uiName, "");
        var newValue = $requestNoInput.val();
        var uiEvent = {
            name: "textChange",
            data: {
                value: newValue
            }
        };

        webForm.triggerEvent(uiName, uiEvent);
    });

    //
    $requestNoInput.on("value", function(_, value) {
        $requestNoInput.val(value);
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $requestNoInput.trigger("chosen:updated");
    });
});


/**
 * TODO description here
 *
 * @author chenxue
 * @create 2016/2/28
 */
$.WebForm.eventAdapter("RequestNoInput", "textChange", "TextInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("RequestNoInput", "focusChange", "TextInput", "refresh", function(data){
    return data.value;
});

$.WebForm.eventAdapter("RequestNoInput", "textChange", "Text", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("RequestNoInput", "focusChange", "Text", "refresh", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author chenxue
 * @create 2016/2/28
 */
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/3/22
 */
$.WebForm.uiRender("SerialnoInput", function($serialnoInput, webForm){
    var uiName = $serialnoInput.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    $serialnoInput.on("input keypress change", function() {
        //
        var newValue = $serialnoInput.val();
        if(newValue==uiValue) {
            return; // 未修改
        }
        //
        webForm.setValue(uiName, newValue);
        //
    });

    //失去焦点后触发自身事件
    $serialnoInput.on("blur", function() {

        var newValue = $serialnoInput.val();
        var uiEvent = {
            name: "focusChange",
            data: {
                value: newValue
            }
        };
        webForm.setInternalValue(uiName, uiEvent.data.value);
        webForm.triggerEvent(uiName, uiEvent);
    });

    //清空自身值并触发焦点失去事件
    $serialnoInput.blur(function() {
        var c_tacode = webForm.getValue("c_tacode");
        var c_fundacco = webForm.getValue("c_fundacco");
        var c_agencyno = webForm.getValue("c_agencyno");
        var c_netno = webForm.getValue("c_netno");
        var c_tradeacco = webForm.getValue("c_tradeacco");
        var c_fundcode = webForm.getValue("c_fundcode");
        var c_sharetype = webForm.getValue("c_sharetype");
        var c_oricserialno = webForm.getValue(uiName);
        //份额强减中，对原确认单号的自身校验
        if (c_oricserialno != '' && c_tacode != '' && c_fundacco != '' &&
            c_agencyno != '' && c_netno != '' && c_tradeacco != '' &&
            c_fundcode != '' && c_sharetype != '') {
            webForm.loadData(uiName, {c_tacode: c_tacode,c_fundacco:c_fundacco,c_agencyno:c_agencyno,c_netno:c_netno,
                c_tradeacco:c_tradeacco,c_fundcode:c_fundcode, c_sharetype:c_sharetype, c_oricserialno: c_oricserialno}, function (data) {
                if (data[0].errorNo != 0) {
                    layer.msg(data[0].errorMes);
                    webForm.setValue(uiName, '');
                }
                var newValue = $serialnoInput.val();
                var uiEvent = {
                    name: "focusChange",
                    data: {
                        value: newValue
                    }
                };
                webForm.triggerEvent(uiName, uiEvent);
            });
        }
    });

    $serialnoInput.on("refresh", function(_, taCode) {
        webForm.setValue(uiName, "");
        var newValue = $serialnoInput.val();
        var uiEvent = {
            name: "focusChange",
            data: {
                value: newValue
            }
        };

        webForm.triggerEvent(uiName, uiEvent);
    });

    //
    $serialnoInput.on("value", function(_, value) {
        $serialnoInput.val(value);
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $serialnoInput.trigger("chosen:updated");
    });
});



/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/3/22
 */
$.WebForm.eventAdapter("SerialnoInput", "focusChange", "AvailableFrozenShareInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("SerialnoInput", "textChange", "AvailableFrozenShareInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("SerialnoInput", "textChange", "TextInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("SerialnoInput", "textChange", "IsDisableInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("SerialnoInput", "focusChange", "TradeCommonSelect", "selectChange", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/3/22
 */
/**
 * 字典项选择组件 (Select模式)
 *
 * @author gavin
 * @create 2015/11/13
 */
$.WebForm.uiRender("SpecPdtOldOnlySelect", function($dictionarySelect, webForm) {
    var uiName = $dictionarySelect.attr("ui-name");
    var readonly = $dictionarySelect.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $dictionarySelect.find("option:first").val());
    }
    if(readonly=="readonly") {
        $dictionarySelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $dictionarySelect.on("change", function() {
        var uiValue = $dictionarySelect.val();
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
        }
    });
    $dictionarySelect.on("filter", function(_, c_fundcode) {
        var c_tacode = webForm.getValue("c_tacode");
        webForm.loadData(uiName,{c_tacode:c_tacode,c_fundcode:c_fundcode},function(result){
            $dictionarySelect.empty();
            if(result.length != 0){
                $dictionarySelect.attr("disabled",false);
                for(var i in result) {
                    var opt = result[i];
                    $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($dictionarySelect);
                }
            }else{
                $('<option value=""></option>').appendTo($dictionarySelect);
                $dictionarySelect.attr("disabled",true);
            }
            $dictionarySelect.change();
            $dictionarySelect.trigger("chosen:updated");
        })
    });
    //
    $dictionarySelect.on("value", function(_, value) {
        /*$dictionarySelect.children("option:selected").siblings().attr("disabled", false);*/
        $dictionarySelect.val(value);
        var uiLabel = $dictionarySelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $dictionarySelect.trigger("chosen:updated");
        /*$dictionarySelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $dictionarySelect.chosen().change(function(){
        if(!$dictionarySelect.attr("disabled")){
            var validator = $(this).parents().find("form").validate();
            validator.element($(this));
        }
    });

    $dictionarySelect.addClass("chosen-select");
});

/**
 * 字典选择组件事件适配
 *
 * @author gavin
 * @create 2015/11/13
 */
$.WebForm.eventAdapter("SpecPdtOldOnlySelect", "selectChange", "SpecPdtOldOnlySelect", "filter", function(data){
    return data.value;
});

$.WebForm.eventAdapter("SpecPdtOldOnlySelect", "selectChange", "FundCodeSelect", "filter", function(data){
    return data.value;
});

$.WebForm.eventAdapter("SpecPdtOldOnlySelect","selectChange","FundAccoInput","refresh",function(data){
    return data.value;
});
$.WebForm.eventAdapter("SpecPdtOldOnlySelect", "selectChange", "DateTimeInput", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("SpecPdtOldOnlySelect","selectChange","TradeCommonSelect","filter",function(data){
    return data.value;
});
$.WebForm.eventAdapter("SpecPdtOldOnlySelect","selectChange","TextInput","refresh",function(data){
    return data.value;
});
$.WebForm.eventAdapter("SpecPdtOldOnlySelect","selectChange","RequestNoInput","refresh",function(data){
    return data.value;
});





/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/13
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/6/3
 */

$.WebForm.uiRender("Text", function($text, webForm){
    var uiName = $text.attr("ui-name");
    //
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    $text.on("refresh", function(_, value) {
        var c_tacode=webForm.getValue("c_tacode");
        var c_fundacco=webForm.getValue("c_fundacco");
        var c_otheracco=webForm.getValue("c_otheracco");
        var c_orirequestno=webForm.getValue("c_orirequestno");

        webForm.loadData(uiName, {c_tacode:c_tacode,
            c_fundacco:c_fundacco,
            c_otheracco:c_otheracco,
            c_orirequestno:c_orirequestno,
            code:value
        }, function(data) {
            var uiValue = '';
            if (data != null && data.length != 0){
                uiValue = data[0][uiName];
            }
            webForm.setValue(uiName, uiValue);
        });
    });

    //
    $text.on("input keypress", function() {
        var uiValue = webForm.getValue(uiName);

        var newValue = $text.val();
        if(newValue==uiValue) {
            return; // 未修改
        }

        webForm.setValue(uiName, newValue);
    });

    //
    $text.on("value", function(_, value) {
        $text.text(value);
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $text.trigger("chosen:updated");
    });
});
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/6/3
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/6/3
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/6/2
 */

$.WebForm.uiRender("TextArea", function($textArea, webForm){
    var uiName = $textArea.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    //
    $textArea.on("input keypress", function() {
        var uiValue = webForm.getValue(uiName);

        var newValue = $textArea.val();
        if(newValue==uiValue) {
            return; // 未修改
        }
        webForm.setValue(uiName, newValue);
    });

    //
    $textArea.on("value", function(_, value) {
        $textArea.val(value);
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $textArea.trigger("chosen:updated");
    });
});
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/6/2
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/6/2
 */
/**
 * 文本输入组件
 *
 * @author Gavin Hu
 * @create 2015/11/17
 */
$.WebForm.uiRender("TextInput", function($textInput, webForm){
    var uiName = $textInput.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    //
    $textInput.on("refresh", function(_, value) {
        var c_tacode=webForm.getValue("c_tacode");
        var c_fundacco=webForm.getValue("c_fundacco");
        var c_otheracco=webForm.getValue("c_otheracco");
        var c_orirequestno=webForm.getValue("c_orirequestno");

        webForm.loadData(uiName, {c_tacode:c_tacode,
            c_fundacco:c_fundacco,
            c_otheracco:c_otheracco,
            c_orirequestno:c_orirequestno,
            code:value
        }, function(data) {
            var uiValue = '';
            if (data != null && data.length != 0){
                uiValue = data[0][uiName];
            }
            webForm.setValue(uiName, uiValue);
        });
    });

    //
    $textInput.on("input keypress", function() {
        var uiValue = webForm.getValue(uiName);

        var newValue = $textInput.val();
        if(newValue==uiValue) {
            return; // 未修改
        }
        webForm.setValue(uiName, newValue);
    });

    //
    $textInput.on("value", function(_, value) {
        $textInput.val(value);
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $textInput.trigger("chosen:updated");
    });
});
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/17
 */
$.WebForm.eventAdapter("TextInput", "textChange", "TextInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TextInput", "textChange", "TradeCommonSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TextInput", "textChange", "BaseMutiSvcDicSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TextInput", "textChange", "MultipeDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TextInput", "textChange", "NotAllMultipeDictionarySelect", "filter", function(data){
    return data.value;
});

$.WebForm.eventAdapter("TextInput", "textChange", "MultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TextInput", "textChange", "NotAllMultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TextInput", "textChange", "FilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TextInput", "textChange", "DefaultAllMultipeFilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TextInput", "textChange", "FilterGradeFundSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TextInput", "textChange", "MultipeStringFilterDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TextInput", "textChange", "FilterNoDftDictionarySelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TextInput", "textChange", "FundCodeSelect", "filter", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/17
 */
/**
 * TODO description here
 *文本区间
 * @author xiaoya10584
 * @create 2016/8/18
 */

$.WebForm.uiRender("TextSectionInput", function ($textSectionInput, webForm) {
    var uiName = $textSectionInput.attr("ui-name");

    var textName1 = 'input[name=' + uiName + '1]';
    var textName2 = 'input[name=' + uiName + '2]';
    var $textSectionInput1 = $textSectionInput.children(textName1);
    var $textSectionInput2 = $textSectionInput.children(textName2);
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    //
    $textSectionInput1.on("input keypress", function () {
        var uiValue = webForm.getValue(uiName);


        var textValue=[];
        var textValue1=$textSectionInput1.val();
        var textValue2=$textSectionInput2.val();
        textValue.push(textValue1);
        textValue.push(textValue2);
        var newValue =textValue;
        if (newValue == uiValue) {
            return; // 未修改
        }

        webForm.setValue(uiName, newValue);

    });

    //
    $textSectionInput2.on("input keypress", function () {
        var uiValue = webForm.getValue(uiName);


        var textValue=[];
        var textValue1=$textSectionInput1.val();
        var textValue2=$textSectionInput2.val();
        textValue.push(textValue1);
        textValue.push(textValue2);
        var newValue =textValue;
        if (newValue == uiValue) {
            return; // 未修改
        }
        webForm.setValue(uiName, newValue);
    });

    $textSectionInput.on("value", function (_, value) {
        $textSectionInput.val(value);
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $textSectionInput.trigger("chosen:updated");
    });


});
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/8/18
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/8/18
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/11/3
 */


$.WebForm.uiRender("ThousandPointsTextInput", function ($thousandPointsTextInput, webForm) {
    var uiName = $thousandPointsTextInput.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, delThousandPoints(uiValue));
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    //
    $thousandPointsTextInput.on("refresh", function (_, value) {
        webForm.loadData(uiName, {
            code: value
        }, function (data) {
            var uiValue = '';
            if (data != null && data.length != 0) {
                uiValue = data[0][uiName];
            }
            webForm.setValue(uiName, uiValue);
        });
    });

    //
    $thousandPointsTextInput.on("input keypress", function () {
        var uiValue = webForm.getValue(uiName);

        var newValue = $thousandPointsTextInput.val();
        if (newValue == uiValue) {
            return; // 未修改
        }

        webForm.setValue(uiName, delThousandPoints(newValue));
    });


    $thousandPointsTextInput.on("blur", function () {
        var uiValue = webForm.getValue(uiName);

        var newValue = $thousandPointsTextInput.val();
        if(newValue.indexOf(',')>=0){
            $thousandPointsTextInput.val(newValue);
        }else{
            $thousandPointsTextInput.val(toThousandPoints(newValue));
        }

    });

    $thousandPointsTextInput.on("focus", function () {

        var newValue = $thousandPointsTextInput.val();
        if(newValue.indexOf(',')>=0){
            $thousandPointsTextInput.val(delThousandPoints(newValue));
        }else{
            $thousandPointsTextInput.val(newValue);
        }

    });
    //
    $thousandPointsTextInput.on("value", function (_, value) {
        $thousandPointsTextInput.val(delThousandPoints(value));
        var uiEvent = {
            name: "textChange",
            data: {
                value: delThousandPoints(value)
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $thousandPointsTextInput.trigger("chosen:updated");
    });


    /**
     * 去掉千分位
     * @param num
     * @returns {*}
     */
    function delThousandPoints(num) {
        if (!!!num) {
            return "";
        }

        if ((num + "").trim() == "") {
            return "";
        }
        var pointIndex = num.lastIndexOf(".");
        var pointPart = "";
        if (pointIndex > -1) {
            pointPart = num.substring(pointIndex , num.length);
            num = num.substring(0, pointIndex);
        }
        var re = /^(-?\d{1,3})((,\d{3})*)$/
        if(re.test(num)){
            num = num.replace(/,/gi, '');
        }
        return num + pointPart;
    }


    /**
     * 转化为千分位
     * @param num
     * @returns {*}
     */
    function toThousandPoints(num) {
        if ((num + "").trim() == "") {
            return "";
        }
        if (isNaN(num)) {
            return "";
        }

        num = num + "";
        if (/^.*\..*$/.test(num)) {
            var pointIndex = num.lastIndexOf(".");
            var intPart = num.substring(0, pointIndex);
            var pointPart = num.substring(pointIndex + 1, num.length);
            intPart = intPart + "";
            var re = /(-?\d+)(\d{3})/
            while (re.test(intPart)) {
                intPart = intPart.replace(re, "$1,$2")
            }
            num = intPart + "." + pointPart;

        } else {
            num = num + "";
            var re = /(-?\d+)(\d{3})/
            while (re.test(num)) {
                num = num.replace(re, "$1,$2")
            }
        }
        return num;

    }
});
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/11/3
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/11/3
 */
/**
 * TODO description here
 *
 * @author xujh13699
 * @create 2015/12/18
 */
$.WebForm.uiRender("TimeInput", function ($TimeInput, webForm) {
    var uiName = $TimeInput.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if (!!uiValue) {
        uiValue = uiValue.replace(new RegExp(":", "gm"), "");
        $TimeInput.val(uiValue.substring(0, 2) + ":" + uiValue.substring(2, 4) + ":" + uiValue.substring(4, 6));
    } else {
        $TimeInput.val(uiValue);
    }
    //
    var timeChange = function (time) {
        //
        time = time.replace(new RegExp(":", "gm"), "");
        webForm.setValue(uiName, time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4, 6));
        //
    };
    var setValues = function (webForm, uiName, uiValue) {
        webForm.context.uiValues[uiName] = uiValue;
        /*        //
         var $ui = webForm.context.uiObjects[uiName];
         //
         $ui.trigger("value", [uiValue]);*/
    }
    //
    $TimeInput.on("click focus", function () {
        var uiValue = webForm.getValue(uiName);
        var time;
        WdatePicker({
            oncleared: function () {
                time = '';
                timeChange(time);
            }, onpicked: function () {
                time = $dp.cal.getNewDateStr();
                timeChange(time);
            }, dateFmt: 'HH:mm:ss', skin: 'twoer', isShowToday: false, errDealMode:0
        })
    });
    //
    $TimeInput.on("input blur", function () {
        var time = $TimeInput.val();
        //
        if(!timecheck(time)){
            time = '';
            $TimeInput.val(time);
        }
        timeChange(time);
    });
    $TimeInput.on("keydown", function () {
        if(13 == event.keyCode){
            var time = $TimeInput.val();
            if(!timecheck(time) || time == ''){
                return false;
            }else{
                timeChange(time);
                return true;
            }
        }
    });


    //
    $TimeInput.on("value", function (_, value) {
        $TimeInput.val(value);
        var uiEvent = {
            name: "timeChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $TimeInput.trigger("chosen:updated");
    });
    function timecheck(time){
        if(time == ''){
            return true;
        }
        var match = /^\d{2}:\d{2}:\d{2}$/;
        if(!match.test(time)){
            return false;
        }
        var spilt = time.split(':');
        if(Number(spilt[0]) < 24 && Number(spilt[1]) < 60 && Number(spilt[2]) < 60){
            return true
        }
        return false;
    }
});

/**
 * TODO description here
 *
 * @author xujh13699
 * @create 2015/12/18
 */
/**
 * TODO description here
 *
 * @author xujh13699
 * @create 2015/12/18
 */
/**
 * 通用选择组件
 *
 * @author chenxue
 * @create 2016/3/07
 */
$.WebForm.uiRender("TradeCommonSelect", function($tradeCommonSelect, webForm){
    var uiName = $tradeCommonSelect.attr("ui-name");
    var uiValue =  webForm.getValue(uiName);
    var readonly = $tradeCommonSelect.attr("readonly");
    //
    $tradeCommonSelect.on("filter", function(_,filterValue){
        //
        var c_tacode=webForm.getValue("c_tacode");
        var c_fundacco = webForm.getValue("c_fundacco");
        var c_agencyno = webForm.getValue("c_agencyno");
        var c_netno = webForm.getValue("c_netno");
        var c_tradeacco = webForm.getValue("c_tradeacco");
        var c_fundcode = webForm.getValue("c_fundcode");
        var c_otheracco = webForm.getValue("c_otheracco");   //非交易过户转入方基金账号
        var c_otheragency = webForm.getValue("c_otheragency");//非交易过户转入方销售商代码
        var c_othernetno = webForm.getValue("c_othernetno");//非交易过户转入方网点代码

        webForm.loadData(uiName, {c_tacode:c_tacode,c_fundacco:c_fundacco,c_agencyno:c_agencyno,c_netno:c_netno,c_tradeacco:c_tradeacco,
            c_fundcode:c_fundcode,c_otheracco:c_otheracco,c_otheragency:c_otheragency,c_othernetno:c_othernetno}, function(data){
            $tradeCommonSelect.empty();
            if(data.length != 0){
                for(var i in data) {
                    var opt = data[i];
                    //tuojg 针对交易账号只显示caption作出的调整
                    if(opt.item.toString().localeCompare("noitem")==0){
                        $('<option value="'+opt.caption+'">'+ opt.caption +'</option>').appendTo($tradeCommonSelect);
                    }else{
                        $('<option value="'+opt.item+'">'+ opt.caption +'</option>').appendTo($tradeCommonSelect);
                    }
                }
            }

            if(uiValue){
                $tradeCommonSelect.val(uiValue);
                webForm.setValue(uiName, uiValue);
            }else if(data.length>0){
                //tuojg 针对交易账号只显示caption作出的调整
                if(data[0].item.toString().localeCompare("noitem")==0){
                    webForm.setValue(uiName, "");
                }else{
                    webForm.setValue(uiName, "");
                }

            }else{
                webForm.setValue(uiName, "");
            }
            //在过滤完数据后手动触发selectChange事件
            var newValue = $tradeCommonSelect.val();
            var uiLabel = $tradeCommonSelect.children("option:selected").text();
            var uiEvent = {
                name: "selectChange",
                data: {
                    label: uiLabel,
                    value: newValue
                }
            };
            if(readonly!="readonly") {
                //
                webForm.triggerEvent(uiName, uiEvent);
            }
            $tradeCommonSelect.trigger("chosen:updated");
        });

    });


    //
    $tradeCommonSelect.on("change", function() {
        var newValue = $tradeCommonSelect.val();
        if(readonly!="readonly") {
            webForm.setValue(uiName, newValue);

        }
    });
    //
    $tradeCommonSelect.on("value", function(_, value) {
        //
        $tradeCommonSelect.val(value);
        var uiLabel = $tradeCommonSelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $tradeCommonSelect.trigger("chosen:updated");
    });


    $tradeCommonSelect.chosen().change(function(){
        if(!$tradeCommonSelect.attr('disable')){
            var validator = $(this).parents().find("form").validate();
            validator.element($(this));
        }
    });

    $tradeCommonSelect.addClass("chosen-select");
});

/**
 * TODO description here
 *
 * @author chenxue
 * @create 2016/3/7
 */
$.WebForm.eventAdapter("TradeCommonSelect", "selectChange", "TradeCommonSelect", "filter", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TradeCommonSelect", "selectChange", "AvailableFrozenShareInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TradeCommonSelect", "selectChange", "AvailableShareInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TradeCommonSelect", "selectChange", "IsDisableInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TradeCommonSelect", "selectChange", "TextInput", "refresh", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author chenxue
 * @create 2016/3/7
 */
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/3/10
 */

$.WebForm.uiRender("TradeRequestNoInput", function($tradeRequestNoInput, webForm){
    var uiName = $tradeRequestNoInput.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    $tradeRequestNoInput.on("input keypress change", function() {
        var uiValue = webForm.getValue(uiName);
        //
        var newValue = $tradeRequestNoInput.val();
        if(newValue==uiValue) {
            return; // 未修改
        }
        //
        //
        webForm.setValue(uiName, newValue);
    });

    $tradeRequestNoInput.blur(function() {
        var c_orirequestno = webForm.getValue(uiName);
        //
        var c_tacode = webForm.getValue("c_tacode");
        //

        if (c_orirequestno != '') {
            webForm.loadData(uiName, {c_tacode: c_tacode, c_orirequestno: c_orirequestno}, function (data) {
                if (data[0].errorNo != 0) {
                    layer.msg(data[0].errorMes);
                    webForm.setValue(uiName, '');
                }
            });
        }

        //ia
    });

    //
    $tradeRequestNoInput.on("value", function(_, value) {
        $tradeRequestNoInput.val(value);
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $tradeRequestNoInput.trigger("chosen:updated");
    });
});



/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/3/10
 */
$.WebForm.eventAdapter("TradeRequestNoInput", "textChange", "TextInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TradeRequestNoInput", "textChange", "Text", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TradeRequestNoInput", "textChange", "AvailableFrozenShareInput", "refresh", function(data){
    return data.value;
});
$.WebForm.eventAdapter("TradeRequestNoInput", "textChange", "FilterDictionarySelect", "filter", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/3/10
 */

/**
 * TODO description here
 *
 * @author xiaoya
 * @create 2015/12/18
 */
$.WebForm.uiRender("TypeSelect", function($typeSelect, webForm) {
    var uiName = $typeSelect.attr("ui-name");
    var readonly = $typeSelect.attr("readonly");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, $typeSelect.find("option:first").val());
    }
    if(readonly=="readonly") {
        $typeSelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $typeSelect.on("change", function() {
        var uiValue = $typeSelect.val();
        var uiLabel = $typeSelect.children("option:selected").text();
        var th_id = 'th#' + uiName.replace('type','') + '_th';
        if(readonly!="readonly") {
            webForm.setValue(uiName, uiValue);
            //
        }
        $(th_id).html(uiLabel);
    });

    //
    $typeSelect.on("value", function(_, value) {
        $typeSelect.val(value);
        var uiLabel = $typeSelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $typeSelect.trigger("chosen:updated");
    });

});


/**
 * TODO description here
 *
 * @author chenxue
 * @create 2015/12/3
 */

/**
 * TODO description here
 *
 * @author chenxue
 * @create 2015/12/3
 */
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/4/20
 */
$.WebForm.uiRender("VariedTextInput", function($variedTextInput, webForm){
    var uiName = $variedTextInput.attr("ui-name");
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    $variedTextInput.on("refresh", function(_, value) {

        var uiName = $variedTextInput.attr("ui-name");
        var c_fundcode=webForm.getValue("c_fundcode");
        var d_cdate=webForm.getValue("d_cdate");
        var d_lastsysdate=webForm.getValue("d_netvaluedate");
        var f_lasttotalshare=webForm.getValue("f_lasttotalshare");
        var f_lasttotalasset=webForm.getValue("f_lasttotalasset");
        var f_netvalue=webForm.getValue("f_netvalue");
        var f_totalnetvalue=webForm.getValue("f_totalnetvalue");

        webForm.loadData(uiName, {c_fundcode:c_fundcode,d_cdate:d_cdate,f_lasttotalshare:f_lasttotalshare,d_lastsysdate:d_lastsysdate,
            f_lasttotalasset:f_lasttotalasset,f_netvalue:f_netvalue,f_totalnetvalue:f_totalnetvalue,uiName:uiName,changeValue:value}, function(data) {
            var uiValue = '';
            if (data != null && data.length != 0) {
                if(uiName == 'f_lasttotalshare'){
                    uiValue = data[0]["lasttotalshare"];
                    webForm.setValue(uiName, uiValue);
                }
                if(uiName == 'f_lasttotalasset'){
                    uiValue = data[0]["lasttotalasset"];
                    webForm.setValue(uiName, uiValue);
                }
                if(uiName == 'f_netvalue'){
                    uiValue = data[0]["netvalue"];
                    webForm.setValue(uiName, uiValue);
                }
                if(uiName == 'f_totalnetvalue'){
                    uiValue = data[0]["totalnetvalue"];
                    webForm.setValue(uiName, uiValue);
                }
            }
        });
        //}
    });
    $variedTextInput.on("input keypress", function() {
        var uiValue = webForm.getValue(uiName);

        var newValue = $variedTextInput.val();
        if(newValue==uiValue) {
            return; // 未修改
        }
        webForm.setValue(uiName, newValue);
    });

    //
    $variedTextInput.on("blur", function() {
        var uiValue = webForm.getValue(uiName);
        var newValue = $variedTextInput.val();
        if(newValue==uiValue) {
            return; // 未修改
        }
        //
        var uiEvent = {
            name: "focusChange",
            data: {
                value: newValue
            }
        };
        //
        webForm.setInternalValue(uiName, uiEvent.data.value);
        webForm.triggerEvent(uiName, uiEvent);
    });

    $variedTextInput.blur(function() {
        var uiValue = webForm.getValue(uiName);
        var newValue = $variedTextInput.val();
        if(newValue==uiValue) {
            return; // 未修改
        }
        //
        var uiEvent = {
            name: "focusChange",
            data: {
                value: newValue
            }
        };
        //
        webForm.setInternalValue(uiName, uiEvent.data.value);
        webForm.triggerEvent(uiName, uiEvent);
    });


    $variedTextInput.on("value", function(_, value) {
        $variedTextInput.val(value);
        var uiEvent = {
            name: "textChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $variedTextInput.trigger("chosen:updated");
    });
});

/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/4/20
 */

$.WebForm.eventAdapter("VariedTextInput", "textChange", "VariedTextInput", "refresh", function(data){
    return data.value;
});
/**
 * TODO description here
 *
 * @author tuojg12929
 * @create 2016/4/20
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/8/18
 */


/**
 * 字典项选择组件 (Checkbox模式)
 *
 * @author Gavin Hu
 * @create 2015/11/16
 */
$.WebForm.uiRender("VerticalDictionaryCheckbox", function($verticalDictionaryCheckbox, webForm) {
    var uiName = $verticalDictionaryCheckbox.attr("ui-name");
    //
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    var dictItemChange = function() {
        var values = [];
        $verticalDictionaryCheckbox.find(":checked").each(function(){
            values.push($(this).val());
        });
        //
        var newValue = values.join(",");
        //
        webForm.setValue(uiName, newValue);
        //
    };
    //
    $verticalDictionaryCheckbox.on("click", "a", function() {
        var checked = $(this).attr("checked");
        if(checked) {
            checked = false;
        } else {
            checked = true;
        }
        $(this).attr("checked", checked);
        //
        $verticalDictionaryCheckbox.find("input[type=checkbox]").each(function() {
            $(this).prop("checked", checked);
        });
        //
        dictItemChange();
    });
    //
    $verticalDictionaryCheckbox.on("change", "input[type=checkbox]", function(){
        dictItemChange();
    });
    //
    $verticalDictionaryCheckbox.on("value", function(_, value){
        var values = value.split(",");
        $verticalDictionaryCheckbox.find("input[type=checkbox]").each(function(){
            var checked = false;
            for(var i in values) {
                if($(this).val()==values[i]) {
                    checked = true;
                }
            }
            //
            $(this).prop("checked", checked);
        });
        var uiEvent = {
            name: "checkboxChange",
            data: {
                value: values
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $verticalDictionaryCheckbox.trigger("chosen:updated");
    });
});
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/8/18
 */
/**
 * TODO description here
 *
 * @author xiaoya10584
 * @create 2016/8/18
 */
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2018/5/17
 */
$.WebForm.uiRender("VueSelect", function ($vueSelect, webForm) {
    var uiName = $vueSelect.attr("ui-name");
    var readonly = $vueSelect.attr("readonly");
    var selectId = $vueSelect.attr("id");

    if (readonly == "readonly") {
        $vueSelect.children("option:selected").siblings().attr("disabled", true);
    }
    //
    $vueSelect.on("change", function () {
        var uiValue = $vueSelect.val();
        var uiLabel = $vueSelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: uiValue
            }
        };
        if (readonly != "readonly") {
            webForm.setInternalValue(uiName, uiEvent.data.value);
            //
            webForm.triggerEvent(uiName, uiEvent);
        }
    });
    $vueSelect.on("filter", function () {
        var uiValue = $vueSelect.val();
        var uiLabel = $vueSelect.children("option:selected").text();
        var uiEvent = {
            name: "selectChange",
            data: {
                label: uiLabel,
                value: uiValue
            }
        };
        if (readonly != "readonly") {
            webForm.setInternalValue(uiName, uiEvent.data.value);
            //
            webForm.triggerEvent(uiName, uiEvent);
        }
    });
    //
    $vueSelect.on("value", function (_, value) {
        /*$vueSelect.children("option:selected").siblings().attr("disabled", false);*/
        $vueSelect.val(value);
        /*$vueSelect.children("option:selected").siblings().attr("disabled", true);*/
    });

    $vueSelect.chosen().change(function () {
        var validator = $(this).parents().find("form").validate();
        validator.element($(this));
    });
    $vueSelect.addClass("chosen-select");

    var vue = new Vue({
        el: '#' + selectId,
        data: {
            dicList: []
        },
        methods: {
            setDicList: function (newData) {
                this.dicList = newData;
                this.$nextTick(function () {
                    $vueSelect.trigger("chosen:updated");
                })
            }
        }
    });
    webForm.loadData(uiName, {}, function (response) {
        vue.setDicList(response);
    });

});

/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2018/5/17
 */
/**
 * TODO description here
 *
 * @author chensy12930
 * @create 2018/5/17
 */
/**
 * 日期时间输入组件
 *
 * @author Gavin Hu
 * @create 2015/11/17
 */
$.WebForm.uiRender("YearInput", function($yearInput, webForm) {
    var uiName = $yearInput.attr("ui-name");
    //
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue.split('-').join(''));
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    var dateChange = function(date){
        //
        webForm.setValue(uiName, date.split('-').join(''));
        //
    };
    var setValues = function(webForm,uiName, uiValue){
        webForm.context.uiValues[uiName] = uiValue;
        //
        var $ui = webForm.context.uiObjects[uiName];
        //
        $ui.trigger("value", [uiValue]);
    }
    //
    $yearInput.on("click", function() {
        var uiValue = webForm.getValue(uiName);
        var settings = {
            choose: function(date) {
                //
                if(date==uiValue) {
                    return ; // 未修改
                }
                //
                dateChange(date);
            },
            format:'YYYY',
            onlyY:true
        };
        //
        $.extend(settings, webForm.getMeta(uiName));
        //
        laydate(settings);
        //
        $("#laydate_ok,#laydate_today,#laydate_clear").on("click", function(){
            var date = $yearInput.val();
            //
            dateChange(date);
        });
    });
    $yearInput.on("filter", function(_, tacode){
        $.ajaxSetup({
            async: false
        });
        //
        var a = tacode;
        if(tacode) {
            webForm.loadData(uiName, {tacode:tacode}, function(data){
                if(data.length>0){
                    $yearInput.val(data[0].item);
                    dateChange(data[0].item);
                }
            });
        }
        $.ajaxSetup({
            async: true
        });
    });
    //
    $yearInput.on("input", function(){
        /*var uiName = $yearInput.attr("ui-name");
         webForm.setInternalValue(uiName, "");
         setValues(webForm,uiName,"");*/
        var date = $yearInput.val();
        //
        dateChange(date);
    });

    //
    $yearInput.on("value", function(_, value){
        $yearInput.val(value);
        var uiEvent = {
            name: "dateChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $yearInput.trigger("chosen:updated");
    });

    $yearInput.on("dateChange",function(){
        var thisForm=$(this).parents().find("form");
        var validator = thisForm.validate();
        validator.element($(this));
    });
});
/**
 * TODO description here
 *
 * @author xujh13699
 * @create 2015/12/18
 */
/**
 * TODO description here
 *
 * @author xujh13699
 * @create 2015/12/18
 */
/**
 * 年月日时间输入组件
 *
 * @author chenxue
 * @create 2016/04/20
 */
$.WebForm.uiRender("YmdTimeInput", function($ymdTimeInput, webForm) {
    var uiName = $ymdTimeInput.attr("ui-name");
    var rpttype = '';
    var uiValue = webForm.getValue(uiName);
    if(!!uiValue){
        webForm.setInternalValue(uiName, uiValue);
    }
    else{
        webForm.setInternalValue(uiName, "");
    }
    //如果有日期类型控件
    $(webForm.form).find("*[ui-name='rpttype']").each(function () {
        if ((rpttype = webForm.getValue('rpttype')) == '') rpttype = $(this).val();
        checkRtpType(rpttype);
    });
    //
    var dateChange = function(date){
        //工作日判定
        webForm.loadData(uiName, {date:date}, function(data){
            if(data.length>0){
                webForm.setValue(uiName,data[0].item);
            }
        });
        //
        webForm.setValue(uiName, date);
    };
    var setValues = function(webForm,uiName, uiValue){
        webForm.context.uiValues[uiName] = uiValue;
        //
        var $ui = webForm.context.uiObjects[uiName];
        //
        $ui.trigger("value", [uiValue]);
    }

    //
    $ymdTimeInput.on("click", function() {
        var uiValue = webForm.getValue(uiName);
        var settings = {
            choose: function(date) {
                if(date==uiValue) {
                    return ; // 未修改
                }
                //
                dateChange(date);
            }
        };
        if(rpttype != '' && ('12').indexOf(rpttype) > -1){
            if(rpttype == '1'){
                settings['format'] = 'YYYY-MM';
            }else{
                settings['format'] = 'YYYY';
            }
        }
        //
        $.extend(settings, webForm.getMeta(uiName));
        //
        laydate(settings);
        $("#laydate_YY,#laydate_MM,#laydate_table").removeAttr('style');
        if(rpttype != '' && ('12').indexOf(rpttype) > -1){
            $("#laydate_table").css("display",'none');
            if(rpttype == '2'){
                $("#laydate_MM").css("display",'none');
            }
        }
        //
        $("#laydate_ok,#laydate_today,#laydate_clear").on("click", function(){
            var date = $ymdTimeInput.val();
            //
            dateChange(date);
        });
    });
    $ymdTimeInput.on("filter", function(_, filterValue){
        $.ajaxSetup({
            async: false
        });
        rpttype = filterValue;
        //如果有日期类型控件
        checkRtpType(rpttype);
        $.ajaxSetup({
            async: true
        });

    });
    //
    $ymdTimeInput.on("input", function(){
        var uiName = $ymdTimeInput.attr("ui-name");
        webForm.setInternalValue(uiName, "");
        setValues(webForm,uiName,"");
        var date = $ymdTimeInput.val();
        //
        dateChange(date);
    });
    /*$ymdTimeInput.on("focus",function(){
     webForm.setInternalValue(uiName, "");
     });*/
    //
    $ymdTimeInput.on("value", function(_, value){
        $ymdTimeInput.val(value);
        var uiEvent = {
            name: "dateChange",
            data: {
                value: value
            }
        };
        webForm.triggerEvent(uiName, uiEvent);
        $ymdTimeInput.trigger("chosen:updated");
    });

    function checkRtpType(rpttype){
        if(rpttype == '0'){
            name = '日期';
        }else if(rpttype == '1'){
            name = '月份';
        }else if(rpttype == '2'){
            name = '年份';
        }else if(rpttype == '3'){
            name = '开始日期';
        }
        $("*[name='"+uiName+"']").val("");//置空
        webForm.setInternalValue(uiName, "");
        if(uiName == 'starttimeinput'){
            $("*[name='"+uiName+"_label']").text(name);
        }else if(uiName == 'endtimeinput'){
            if(rpttype == '3'){
                $("*[name='"+uiName+"_label']").removeAttr('style');
                $("*[name='"+uiName+"']").removeAttr('style');
            }else{
                $("*[name='"+uiName+"_label']").css('display', 'none');
                $("*[name='"+uiName+"']").css('display', 'none');
            }
        }
    }

    $ymdTimeInput.on("dateChange",function(){
        var thisForm=$(this).parents().find("form");
        var validator = thisForm.validate();
        validator.element($(this));
    });
});
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/17
 */
/**
 * TODO description here
 *
 * @author gavin
 * @create 2015/11/17
 */
});