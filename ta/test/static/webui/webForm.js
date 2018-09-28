/**
 * Web Form 核心JS
 *
 * @author Gavin Hu
 * @create 2015/12/14
 */
(function ($) {
    //
    $.fn.webForm = function (options) {
        assertForm(this);
        //
        console.log($.WebForm)
        var webForm = $.data(this[0], "webForm");
        if (webForm) {
            return webForm;
        }
        //
        webForm = new $.WebForm(options, this[0]);
        $.data(this[0], "webForm", webForm);
        //
        return webForm;
    };
    //
    function assertForm($element) {
        var tagName = $element.prop("tagName");
        if (tagName != "FORM") {
            throw new Error("Target must be form!");
        }
    }
    //
    $.WebForm = function (options, form) {
        this.options = $.extend({}, options);
        this.form = form;
        this.context = {
            uiNames: [],
            uiTypes: {},
            uiValues: {},
            uiMetaMap : {},
            uiDataMap : {},
            uiObjects: {}
        },
        //
        this.init();
    };
    $.extend($.WebForm, {
        _renders: {},
        _adapters: {},
        prototype: {
            init: function () {
                // render ui
                var webForm = this;
                $(this.form).find("*[ui-type]").each(function () {
                    var $ui = $(this);
                    webForm.uiContext($ui);
                    //
                    webForm.renderUI($ui);
                });
                // bind event
                $(this.form).find("*[ui-event]").each(function () {
                    var uiEvent = $.parseJSON($(this).attr("ui-event"));
                    //
                    webForm.bindEvent(uiEvent);
                });
            },
            uiContext: function ($ui) {
                var uiName = $ui.attr("ui-name");
                var uiType = $ui.attr("ui-type");
                var uiValue = $ui.attr("ui-value");
                var uiMeta = $.parseJSON($ui.attr("ui-meta"));
                var uiData = $.parseJSON($ui.attr("ui-data"));
                //
                this.context.uiNames.push(uiName);
                this.context.uiTypes[uiName] = uiType;
                this.context.uiValues[uiName] = uiValue;
                this.context.uiMetaMap[uiName] = uiMeta;
                this.context.uiDataMap[uiName] = uiData;
                this.context.uiObjects[uiName] = $ui;
            },
            renderUI: function ($ui) {
                var uiType = $ui.attr("ui-type");
                //
                var render = $.WebForm._renders[uiType];
                if (render) {
                    render($ui, this);
                }
            },
            bindEvent: function (uiEvent) {
                var eventSource = uiEvent.eventSource;
                var eventTarget = uiEvent.eventTarget;
                var uiTypeSource = this.context.uiTypes[eventSource.uiName];
                var uiEventSource = eventSource.uiEvent;
                var uiTypeTarget = this.context.uiTypes[eventTarget.uiName];
                var uiEventTarget = eventTarget.uiEvent;
                //
                var webForm = this;
                $("*[ui-name=" + eventSource.uiName + "]").on(eventSource.uiEvent, function (_, event) {
                    if(uiTypeSource && uiEventSource && uiTypeTarget && uiEventTarget) {
                        var eventAdapter = $.WebForm.eventAdapter(uiTypeSource, uiEventSource, uiTypeTarget, uiEventTarget);
                        if (eventAdapter) {
                            $("*[ui-name=" + eventTarget.uiName + "]").trigger(uiEventTarget, eventAdapter(event.data));
                        } else {
                            //
                            //alert("Adapter not registered!");
                        }
                    }
                });
            },
            getMeta : function(uiName) {
                return this.context.uiMetaMap[uiName];
            },
            getData : function(uiName) {
                return this.context.uiDataMap[uiName];
            },
            getNames : function () {
                return this.context.uiNames;
            },
            getValues : function() {
                return this.context.uiValues;
            },
            getValue: function(uiName) {
                return this.context.uiValues[uiName];
            },
            setValue: function (uiName, uiValue) {
                if(uiName in this.context.uiValues) {
                    this.context.uiValues[uiName] = uiValue;
                    //
                    var $ui = this.context.uiObjects[uiName];
                    //
                    $ui.trigger("value", [uiValue]);
                }
            },
            setInternalValue : function(uiName, uiValue) {
                if(uiName in this.context.uiValues) {
                    this.context.uiValues[uiName] = uiValue;
                }
            },
            triggerEvent : function(uiName, uiEvent) {
                var $ui = this.context.uiObjects[uiName];
                $ui.trigger(uiEvent.name, [uiEvent]);
            },
            reset : function() {
                var names = this.getNames();
                for(var i in names) {
                    var name = names[i];
                    this.setValue(name, "");
                }
            },
            refresh: function () {
                for (var i in this.context.uiNames) {
                    var uiName = this.context.uiNames[i];
                    var $ui = this.context.uiObjects[uiName];
                    //
                    $ui.trigger("refresh", [this.context]);
                }
            },
            loadData : function(uiName, param, callback) {
                var $ui = this.context.uiObjects[uiName];
                var uiType = this.context.uiTypes[uiName];
                var uiMeta = $ui.attr("ui-meta");
                //
                if(uiType && callback) {
                    var contextPath = this.options["contextPath"];
                    if(contextPath) {
                        $.ajax({
                            dataType: "json",
                            data: param ? param : {},
                            url: contextPath + "/web/component.json",
                            headers: {
                                "UI-Name" : uiName,
                                "UI-Type" : uiType,
                                "UI-Meta" : uiMeta
                            },
                            success : callback
                        });
                    } else {
                        alert("Please set contextPath with options!");
                    }
                }
            }
        },
        uiRender: function (uiType, uiRender) {
            if (uiRender) {
                $.WebForm._renders[uiType] = uiRender;
            }
            return $.WebForm._renders[uiType];
        },
        eventAdapter: function (uiTypeSource, uiEventSource, uiTypeTarget, uiEventTarget, uiEventAdapter) {
            var key = uiTypeSource + "_" + uiEventSource + "_" + uiTypeTarget + "+" + uiEventTarget + "_Adapter";
            if (uiEventAdapter) {
                $.WebForm._adapters[key] = uiEventAdapter;
            }
            return $.WebForm._adapters[key];
        }
    });
}(jQuery));