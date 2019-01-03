;(function (root, factory) {
    if (typeof define === 'function' && define.amd) {
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        module.exports = factory(require('jquery'));
    } else {
        root.jquery_mmenu_js = factory(root.jQuery);
    }
}(this, function (jQuery) {
    /*!
 * jQuery mmenu v7.2.2
 * @requires jQuery 1.7.0 or later
 *
 * mmenu.frebsite.nl
 *
 * Copyright (c) Fred Heusschen
 * www.frebsite.nl
 *
 * License: CC-BY-NC-4.0
 * http://creativecommons.org/licenses/by-nc/4.0/
 */
    !function (h) {
        var n, p, o, a, t, f = "mmenu", e = "7.2.2";
        h[f] && h[f].version > e || (h[f] = function (t, e, n) {
            return this.$menu = t, this._api = ["bind", "getInstance", "initPanels", "openPanel", "closePanel", "closeAllPanels", "setSelected"], this.opts = e, this.conf = n, this.vars = {}, this.cbck = {}, this.mtch = {}, "function" == typeof this.___deprecated && this.___deprecated(), this._initWrappers(), this._initAddons(), this._initExtensions(), this._initHooks(), this._initMenu(), this._initPanels(), this._initOpened(), this._initAnchors(), this._initMatchMedia(), "function" == typeof this.___debug && this.___debug(), this
        }, h[f].version = e, h[f].uniqueId = 0, h[f].wrappers = {}, h[f].addons = {}, h[f].defaults = {
            hooks: {},
            extensions: [],
            wrappers: [],
            navbar: {add: !0, title: "", titleLink: "parent"},
            onClick: {setSelected: !0},
            slidingSubmenus: !0
        }, h[f].configuration = {
            classNames: {
                divider: "Divider",
                inset: "Inset",
                nolistview: "NoListview",
                nopanel: "NoPanel",
                panel: "Panel",
                selected: "Selected",
                spacer: "Spacer",
                vertical: "Vertical"
            }, clone: !1, language: null, openingInterval: 25, panelNodetype: "ul, ol, div", transitionDuration: 400
        }, h[f].prototype = {
            getInstance: function () {
                return this
            }, initPanels: function (t) {
                this._initPanels(t)
            }, openPanel: function (t, e) {
                if (this.trigger("openPanel:before", t), t && t.length && (t.is("." + p.panel) || (t = t.closest("." + p.panel)), t.is("." + p.panel))) {
                    var n = this;
                    if ("boolean" != typeof e && (e = !0), t.parent("." + p.listitem + "_vertical").length) t.parents("." + p.listitem + "_vertical").addClass(p.listitem + "_opened").children("." + p.panel).removeClass(p.hidden), this.openPanel(t.parents("." + p.panel).not(function () {
                        return h(this).parent("." + p.listitem + "_vertical").length
                    }).first()), this.trigger("openPanel:start", t), this.trigger("openPanel:finish", t); else {
                        if (t.hasClass(p.panel + "_opened")) return;
                        var i = this.$pnls.children("." + p.panel), s = this.$pnls.children("." + p.panel + "_opened");
                        if (!h[f].support.csstransitions) return s.addClass(p.hidden).removeClass(p.panel + "_opened"), t.removeClass(p.hidden).addClass(p.panel + "_opened"), this.trigger("openPanel:start", t), void this.trigger("openPanel:finish", t);
                        i.not(t).removeClass(p.panel + "_opened-parent");
                        for (var a = t.data(o.parent); a;) (a = a.closest("." + p.panel)).parent("." + p.listitem + "_vertical").length || a.addClass(p.panel + "_opened-parent"), a = a.data(o.parent);
                        i.removeClass(p.panel + "_highest").not(s).not(t).addClass(p.hidden), t.removeClass(p.hidden);
                        var r = function () {
                            s.removeClass(p.panel + "_opened"), t.addClass(p.panel + "_opened"), t.hasClass(p.panel + "_opened-parent") ? (s.addClass(p.panel + "_highest"), t.removeClass(p.panel + "_opened-parent")) : (s.addClass(p.panel + "_opened-parent"), t.addClass(p.panel + "_highest")), n.trigger("openPanel:start", t)
                        }, l = function () {
                            s.removeClass(p.panel + "_highest").addClass(p.hidden), t.removeClass(p.panel + "_highest"), n.trigger("openPanel:finish", t)
                        };
                        e && !t.hasClass(p.panel + "_noanimation") ? setTimeout(function () {
                            n.__transitionend(t, function () {
                                l()
                            }, n.conf.transitionDuration), r()
                        }, n.conf.openingInterval) : (r(), l())
                    }
                    this.trigger("openPanel:after", t)
                }
            }, closePanel: function (t) {
                this.trigger("closePanel:before", t);
                var e = t.parent();
                e.hasClass(p.listitem + "_vertical") && (e.removeClass(p.listitem + "_opened"), t.addClass(p.hidden), this.trigger("closePanel", t)), this.trigger("closePanel:after", t)
            }, closeAllPanels: function (t) {
                this.trigger("closeAllPanels:before"), this.$pnls.find("." + p.listview).children().removeClass(p.listitem + "_selected").filter("." + p.listitem + "_vertical").removeClass(p.listitem + "_opened");
                var e = this.$pnls.children("." + p.panel), n = t && t.length ? t : e.first();
                this.$pnls.children("." + p.panel).not(n).removeClass(p.panel + "_opened").removeClass(p.panel + "_opened-parent").removeClass(p.panel + "_highest").addClass(p.hidden), this.openPanel(n, !1), this.trigger("closeAllPanels:after")
            }, togglePanel: function (t) {
                var e = t.parent();
                e.hasClass(p.listitem + "_vertical") && this[e.hasClass(p.listitem + "_opened") ? "closePanel" : "openPanel"](t)
            }, setSelected: function (t) {
                this.trigger("setSelected:before", t), this.$menu.find("." + p.listitem + "_selected").removeClass(p.listitem + "_selected"), t.addClass(p.listitem + "_selected"), this.trigger("setSelected:after", t)
            }, bind: function (t, e) {
                this.cbck[t] = this.cbck[t] || [], this.cbck[t].push(e)
            }, trigger: function () {
                var t = Array.prototype.slice.call(arguments), e = t.shift();
                if (this.cbck[e]) for (var n = 0, i = this.cbck[e].length; n < i; n++) this.cbck[e][n].apply(this, t)
            }, matchMedia: function (t, e, n) {
                var i = {yes: e, no: n};
                this.mtch[t] = this.mtch[t] || [], this.mtch[t].push(i)
            }, i18n: function (t) {
                return h[f].i18n(t, this.conf.language)
            }, _initHooks: function () {
                for (var t in this.opts.hooks) this.bind(t, this.opts.hooks[t])
            }, _initWrappers: function () {
                this.trigger("initWrappers:before");
                for (var t = 0; t < this.opts.wrappers.length; t++) {
                    var e = h[f].wrappers[this.opts.wrappers[t]];
                    "function" == typeof e && e.call(this)
                }
                this.trigger("initWrappers:after")
            }, _initAddons: function () {
                var t;
                for (t in this.trigger("initAddons:before"), h[f].addons) h[f].addons[t].add.call(this), h[f].addons[t].add = function () {
                };
                for (t in h[f].addons) h[f].addons[t].setup.call(this);
                this.trigger("initAddons:after")
            }, _initExtensions: function () {
                this.trigger("initExtensions:before");
                var e = this;
                for (var t in this.opts.extensions.constructor === Array && (this.opts.extensions = {all: this.opts.extensions}), this.opts.extensions) this.opts.extensions[t] = this.opts.extensions[t].length ? p.menu + "_" + this.opts.extensions[t].join(" " + p.menu + "_") : "", this.opts.extensions[t] && function (t) {
                    e.matchMedia(t, function () {
                        this.$menu.addClass(this.opts.extensions[t])
                    }, function () {
                        this.$menu.removeClass(this.opts.extensions[t])
                    })
                }(t);
                this.trigger("initExtensions:after")
            }, _initMenu: function () {
                this.trigger("initMenu:before");
                this.conf.clone && (this.$orig = this.$menu, this.$menu = this.$orig.clone(), this.$menu.add(this.$menu.find("[id]")).filter("[id]").each(function () {
                    h(this).attr("id", p.mm(h(this).attr("id")))
                })), this.$menu.attr("id", this.$menu.attr("id") || this.__getUniqueId()), this.$pnls = h('<div class="' + p.panels + '" />').append(this.$menu.children(this.conf.panelNodetype)).prependTo(this.$menu), this.$menu.addClass(p.menu).parent().addClass(p.wrapper), this.trigger("initMenu:after")
            }, _initPanels: function (t) {
                this.trigger("initPanels:before", t), t = t || this.$pnls.children(this.conf.panelNodetype);
                var i = h(), s = this, a = function (t) {
                    t.filter(s.conf.panelNodetype).each(function (t) {
                        var e = s._initPanel(h(this));
                        if (e) {
                            s._initNavbar(e), s._initListview(e), i = i.add(e);
                            var n = e.children("." + p.listview).children("li").children(s.conf.panelNodetype).add(e.children("." + s.conf.classNames.panel));
                            n.length && a(n)
                        }
                    })
                };
                a(t), this.trigger("initPanels:after", i)
            }, _initPanel: function (t) {
                this.trigger("initPanel:before", t);
                if (t.hasClass(p.panel)) return t;
                if (this.__refactorClass(t, this.conf.classNames.panel, p.panel), this.__refactorClass(t, this.conf.classNames.nopanel, p.nopanel), this.__refactorClass(t, this.conf.classNames.inset, p.listview + "_inset"), t.filter("." + p.listview + "_inset").addClass(p.nopanel), t.hasClass(p.nopanel)) return !1;
                var e = t.hasClass(this.conf.classNames.vertical) || !this.opts.slidingSubmenus;
                t.removeClass(this.conf.classNames.vertical);
                var n = t.attr("id") || this.__getUniqueId();
                t.is("ul, ol") && (t.removeAttr("id"), t.wrap("<div />"), t = t.parent()), t.attr("id", n), t.addClass(p.panel + " " + p.hidden);
                var i = t.parent("li");
                return e ? i.addClass(p.listitem + "_vertical") : t.appendTo(this.$pnls), i.length && (i.data(o.child, t), t.data(o.parent, i)), this.trigger("initPanel:after", t), t
            }, _initNavbar: function (t) {
                if (this.trigger("initNavbar:before", t), !t.children("." + p.navbar).length) {
                    var e = t.data(o.parent), n = h('<div class="' + p.navbar + '" />'),
                        i = this.__getPanelTitle(t, this.opts.navbar.title), s = "";
                    if (e && e.length) {
                        if (e.hasClass(p.listitem + "_vertical")) return;
                        if (e.parent().is("." + p.listview)) var a = e.children("a, span").not("." + p.btn + "_next"); else a = e.closest("." + p.panel).find('a[href="#' + t.attr("id") + '"]');
                        var r = (e = (a = a.first()).closest("." + p.panel)).attr("id");
                        switch (i = this.__getPanelTitle(t, h("<span>" + a.text() + "</span>").text()), this.opts.navbar.titleLink) {
                            case"anchor":
                                s = a.attr("href");
                                break;
                            case"parent":
                                s = "#" + r
                        }
                        n.append('<a class="' + p.btn + " " + p.btn + "_prev " + p.navbar + '__btn" href="#' + r + '" />')
                    } else if (!this.opts.navbar.title) return;
                    this.opts.navbar.add && t.addClass(p.panel + "_has-navbar"), n.append('<a class="' + p.navbar + '__title"' + (s.length ? ' href="' + s + '"' : "") + ">" + i + "</a>").prependTo(t), this.trigger("initNavbar:after", t)
                }
            }, _initListview: function (t) {
                this.trigger("initListview:before", t);
                var e = this.__childAddBack(t, "ul, ol");
                this.__refactorClass(e, this.conf.classNames.nolistview, p.nolistview);
                var n = e.not("." + p.nolistview).addClass(p.listview).children().addClass(p.listitem);
                this.__refactorClass(n, this.conf.classNames.selected, p.listitem + "_selected"), this.__refactorClass(n, this.conf.classNames.divider, p.listitem + "_divider"), this.__refactorClass(n, this.conf.classNames.spacer, p.listitem + "_spacer"), n.children("a, span").not("." + p.btn).addClass(p.listitem + "__text");
                var i = t.data(o.parent);
                if (i && i.is("." + p.listitem) && !i.children("." + p.btn).length) {
                    var s = i.children("a, span").first(),
                        a = h('<a class="' + p.btn + " " + p.btn + "_next " + p.listitem + '__btn" href="#' + t.attr("id") + '" />');
                    a.insertAfter(s), s.is("span") && (a.addClass(p.listitem + "__text").html(s.html()), s.remove())
                }
                this.trigger("initListview:after", t)
            }, _initOpened: function () {
                this.trigger("initOpened:before");
                var t = this.$pnls.find("." + p.listitem + "_selected").removeClass(p.listitem + "_selected").last().addClass(p.listitem + "_selected"),
                    e = t.length ? t.closest("." + p.panel) : this.$pnls.children("." + p.panel).first();
                this.openPanel(e, !1), this.trigger("initOpened:after")
            }, _initAnchors: function () {
                this.trigger("initAnchors:before");
                var c = this;
                t.$body.on(a.click + "-oncanvas", "a[href]", function (t) {
                    var e = h(this), n = e.attr("href"), i = c.$menu.find(e).length,
                        s = e.is("." + p.listitem + " > a"), a = e.is('[rel="external"]') || e.is('[target="_blank"]');
                    if (i && 1 < n.length && "#" == n.slice(0, 1)) try {
                        var r = c.$menu.find(n);
                        if (r.is("." + p.panel)) return c[e.parent().hasClass(p.listitem + "_vertical") ? "togglePanel" : "openPanel"](r), void t.preventDefault()
                    } catch (t) {
                    }
                    var l = {close: null, setSelected: null, preventDefault: "#" == n.slice(0, 1)};
                    for (var o in h[f].addons) {
                        var d = h[f].addons[o].clickAnchor.call(c, e, i, s, a);
                        if (d) {
                            if ("boolean" == typeof d) return void t.preventDefault();
                            "object" == typeof d && (l = h.extend({}, l, d))
                        }
                    }
                    i && s && !a && (c.__valueOrFn(e, c.opts.onClick.setSelected, l.setSelected) && c.setSelected(h(t.target).parent()), c.__valueOrFn(e, c.opts.onClick.preventDefault, l.preventDefault) && t.preventDefault(), c.__valueOrFn(e, c.opts.onClick.close, l.close) && c.opts.offCanvas && "function" == typeof c.close && c.close())
                }), this.trigger("initAnchors:after")
            }, _initMatchMedia: function () {
                var n = this;
                for (var i in this.mtch) !function () {
                    var e = i, t = window.matchMedia(e);
                    n._fireMatchMedia(e, t), t.addListener(function (t) {
                        n._fireMatchMedia(e, t)
                    })
                }()
            }, _fireMatchMedia: function (t, e) {
                for (var n = e.matches ? "yes" : "no", i = 0; i < this.mtch[t].length; i++) this.mtch[t][i][n].call(this)
            }, _getOriginalMenuId: function () {
                var t = this.$menu.attr("id");
                return this.conf.clone && t && t.length && (t = p.umm(t)), t
            }, __api: function () {
                var n = this, i = {};
                return h.each(this._api, function (t) {
                    var e = this;
                    i[e] = function () {
                        var t = n[e].apply(n, arguments);
                        return void 0 === t ? i : t
                    }
                }), i
            }, __valueOrFn: function (t, e, n) {
                if ("function" == typeof e) {
                    var i = e.call(t[0]);
                    if (void 0 !== i) return i
                }
                return "function" != typeof e && void 0 !== e || void 0 === n ? e : n
            }, __getPanelTitle: function (t, e) {
                var n;
                return "function" == typeof this.opts.navbar.title && (n = this.opts.navbar.title.call(t[0])), void 0 === n && (n = t.data(o.title)), void 0 !== n ? n : "string" == typeof e ? this.i18n(e) : this.i18n(h[f].defaults.navbar.title)
            }, __refactorClass: function (t, e, n) {
                return t.filter("." + e).removeClass(e).addClass(n)
            }, __findAddBack: function (t, e) {
                return t.find(e).add(t.filter(e))
            }, __childAddBack: function (t, e) {
                return t.children(e).add(t.filter(e))
            }, __filterListItems: function (t) {
                return t.not("." + p.listitem + "_divider").not("." + p.hidden)
            }, __filterListItemAnchors: function (t) {
                return this.__filterListItems(t).children("a").not("." + p.btn + "_next")
            }, __openPanelWoAnimation: function (t) {
                t.hasClass(p.panel + "_noanimation") || (t.addClass(p.panel + "_noanimation"), this.__transitionend(t, function () {
                    t.removeClass(p.panel + "_noanimation")
                }, this.conf.openingInterval), this.openPanel(t))
            }, __transitionend: function (e, n, t) {
                var i = !1, s = function (t) {
                    void 0 !== t && t.target != e[0] || (i || (e.off(a.transitionend), e.off(a.webkitTransitionEnd), n.call(e[0])), i = !0)
                };
                e.on(a.transitionend, s), e.on(a.webkitTransitionEnd, s), setTimeout(s, 1.1 * t)
            }, __getUniqueId: function () {
                return p.mm(h[f].uniqueId++)
            }
        }, h.fn[f] = function (n, i) {
            !function () {
                if (h[f].glbl) return;
                t = {
                    $wndw: h(window),
                    $docu: h(document),
                    $html: h("html"),
                    $body: h("body")
                }, p = {}, o = {}, a = {}, h.each([p, o, a], function (t, i) {
                    i.add = function (t) {
                        t = t.split(" ");
                        for (var e = 0, n = t.length; e < n; e++) i[t[e]] = i.mm(t[e])
                    }
                }), p.mm = function (t) {
                    return "mm-" + t
                }, p.add("wrapper menu panels panel nopanel navbar listview nolistview listitem btn hidden"), p.umm = function (t) {
                    return "mm-" == t.slice(0, 3) && (t = t.slice(3)), t
                }, o.mm = function (t) {
                    return "mm-" + t
                }, o.add("parent child title"), a.mm = function (t) {
                    return t + ".mm"
                }, a.add("transitionend webkitTransitionEnd click scroll resize keydown mousedown mouseup touchstart touchmove touchend orientationchange"), h[f]._c = p, h[f]._d = o, h[f]._e = a, h[f].glbl = t
            }(), n = h.extend(!0, {}, h[f].defaults, n), i = h.extend(!0, {}, h[f].configuration, i);
            var s = h();
            return this.each(function () {
                var t = h(this);
                if (!t.data(f)) {
                    var e = new h[f](t, n, i);
                    e.$menu.data(f, e.__api()), s = s.add(e.$menu)
                }
            }), s
        }, h[f].i18n = (n = {}, function (t, e) {
            switch (typeof t) {
                case"object":
                    return "string" == typeof e && (void 0 === n[e] && (n[e] = {}), h.extend(n[e], t)), n;
                case"string":
                    return "string" == typeof e && void 0 !== n[e] && n[e][t] || t;
                case"undefined":
                default:
                    return n
            }
        }), h[f].support = {
            touch: "ontouchstart" in window || navigator.msMaxTouchPoints || !1,
            csstransitions: "undefined" == typeof Modernizr || void 0 === Modernizr.csstransitions || Modernizr.csstransitions
        })
    }(jQuery);
    !function (r) {
        var s, i, o, a, t = "mmenu", p = "offCanvas";
        r[t].addons[p] = {
            setup: function () {
                if (this.opts[p]) {
                    var e = this.opts[p], i = this.conf[p];
                    a = r[t].glbl, this._api = r.merge(this._api, ["open", "close", "setPage"]), "object" != typeof e && (e = {}), e = this.opts[p] = r.extend(!0, {}, r[t].defaults[p], e), "string" != typeof i.page.selector && (i.page.selector = "> " + i.page.nodetype), this.vars.opened = !1;
                    var o = [s.menu + "_offcanvas"];
                    this.bind("initMenu:after", function () {
                        var e = this;
                        this._initBlocker(), this.setPage(a.$page), this["_initWindow_" + p](), this.$menu.addClass(o.join(" ")).parent("." + s.wrapper).removeClass(s.wrapper), this.$menu[i.menu.insertMethod](i.menu.insertSelector);
                        var t = window.location.hash;
                        if (t) {
                            var n = this._getOriginalMenuId();
                            n && n == t.slice(1) && setTimeout(function () {
                                e.open()
                            }, 1e3)
                        }
                    }), this.bind("setPage:after", function (e) {
                        a.$blck && a.$blck.children("a").attr("href", "#" + e.attr("id"))
                    }), this.bind("open:start:sr-aria", function () {
                        this.__sr_aria(this.$menu, "hidden", !1)
                    }), this.bind("close:finish:sr-aria", function () {
                        this.__sr_aria(this.$menu, "hidden", !0)
                    }), this.bind("initMenu:after:sr-aria", function () {
                        this.__sr_aria(this.$menu, "hidden", !0)
                    }), this.bind("initBlocker:after:sr-text", function () {
                        a.$blck.children("a").html(this.__sr_text(this.i18n(this.conf.screenReader.text.closeMenu)))
                    })
                }
            }, add: function () {
                s = r[t]._c, i = r[t]._d, o = r[t]._e, s.add("slideout page no-csstransforms3d"), i.add("style")
            }, clickAnchor: function (e, t) {
                var n = this;
                if (this.opts[p]) {
                    var i = this._getOriginalMenuId();
                    if (i && e.is('[href="#' + i + '"]')) {
                        if (t) return this.open(), !0;
                        var o = e.closest("." + s.menu);
                        if (o.length) {
                            var r = o.data("mmenu");
                            if (r && r.close) return r.close(), n.__transitionend(o, function () {
                                n.open()
                            }, n.conf.transitionDuration), !0
                        }
                        return this.open(), !0
                    }
                    if (a.$page) return (i = a.$page.first().attr("id")) && e.is('[href="#' + i + '"]') ? (this.close(), !0) : void 0
                }
            }
        }, r[t].defaults[p] = {
            blockUI: !0,
            moveBackground: !0
        }, r[t].configuration[p] = {
            menu: {insertMethod: "prependTo", insertSelector: "body"},
            page: {nodetype: "div", selector: null, noSelector: [], wrapIfNeeded: !0}
        }, r[t].prototype.open = function () {
            if (this.trigger("open:before"), !this.vars.opened) {
                var e = this;
                this._openSetup(), setTimeout(function () {
                    e._openFinish()
                }, this.conf.openingInterval), this.trigger("open:after")
            }
        }, r[t].prototype._openSetup = function () {
            var e = this, t = this.opts[p];
            this.closeAllOthers(), a.$page.each(function () {
                r(this).data(i.style, r(this).attr("style") || "")
            }), a.$wndw.trigger(o.resize + "-" + p, [!0]);
            var n = [s.wrapper + "_opened"];
            t.blockUI && n.push(s.wrapper + "_blocking"), "modal" == t.blockUI && n.push(s.wrapper + "_modal"), t.moveBackground && n.push(s.wrapper + "_background"), a.$html.addClass(n.join(" ")), setTimeout(function () {
                e.vars.opened = !0
            }, this.conf.openingInterval), this.$menu.addClass(s.menu + "_opened")
        }, r[t].prototype._openFinish = function () {
            var e = this;
            this.__transitionend(a.$page.first(), function () {
                e.trigger("open:finish")
            }, this.conf.transitionDuration), this.trigger("open:start"), a.$html.addClass(s.wrapper + "_opening")
        }, r[t].prototype.close = function () {
            if (this.trigger("close:before"), this.vars.opened) {
                var t = this;
                this.__transitionend(a.$page.first(), function () {
                    t.$menu.removeClass(s.menu + "_opened");
                    var e = [s.wrapper + "_opened", s.wrapper + "_blocking", s.wrapper + "_modal", s.wrapper + "_background"];
                    a.$html.removeClass(e.join(" ")), a.$page.each(function () {
                        var e = r(this).data(i.style);
                        r(this).attr("style", e)
                    }), t.vars.opened = !1, t.trigger("close:finish")
                }, this.conf.transitionDuration), this.trigger("close:start"), a.$html.removeClass(s.wrapper + "_opening"), this.trigger("close:after")
            }
        }, r[t].prototype.closeAllOthers = function () {
            a.$body.find("." + s.menu + "_offcanvas").not(this.$menu).each(function () {
                var e = r(this).data(t);
                e && e.close && e.close()
            })
        }, r[t].prototype.setPage = function (e) {
            this.trigger("setPage:before", e);
            var t = this, n = this.conf[p];
            e && e.length || (e = a.$body.find(n.page.selector).not("." + s.menu).not("." + s.wrapper + "__blocker"), n.page.noSelector.length && (e = e.not(n.page.noSelector.join(", "))), 1 < e.length && n.page.wrapIfNeeded && (e = e.wrapAll("<" + this.conf[p].page.nodetype + " />").parent())), e.addClass(s.page + " " + s.slideout).each(function () {
                r(this).attr("id", r(this).attr("id") || t.__getUniqueId())
            }), a.$page = e, this.trigger("setPage:after", e)
        }, r[t].prototype["_initWindow_" + p] = function () {
            a.$wndw.off(o.keydown + "-" + p).on(o.keydown + "-" + p, function (e) {
                if (a.$html.hasClass(s.wrapper + "_opened") && 9 == e.keyCode) return e.preventDefault(), !1
            });
            var i = 0;
            a.$wndw.off(o.resize + "-" + p).on(o.resize + "-" + p, function (e, t) {
                if (1 == a.$page.length && (t || a.$html.hasClass(s.wrapper + "_opened"))) {
                    var n = a.$wndw.height();
                    (t || n != i) && (i = n, a.$page.css("minHeight", n))
                }
            })
        }, r[t].prototype._initBlocker = function () {
            var t = this, e = this.opts[p], n = this.conf[p];
            this.trigger("initBlocker:before"), e.blockUI && (a.$blck || (a.$blck = r('<div class="' + s.wrapper + "__blocker " + s.slideout + '" />').append("<a />")), a.$blck.appendTo(n.menu.insertSelector).off(o.touchstart + "-" + p + " " + o.touchmove + "-" + p).on(o.touchstart + "-" + p + " " + o.touchmove + "-" + p, function (e) {
                e.preventDefault(), e.stopPropagation(), a.$blck.trigger(o.mousedown + "-" + p)
            }).off(o.mousedown + "-" + p).on(o.mousedown + "-" + p, function (e) {
                e.preventDefault(), a.$html.hasClass(s.wrapper + "_modal") || (t.closeAllOthers(), t.close())
            }), this.trigger("initBlocker:after"))
        }
    }(jQuery);
    !function (n) {
        var s, o, i = "mmenu", e = "screenReader";
        n[i].addons[e] = {
            setup: function () {
                var r = this, t = this.opts[e], a = this.conf[e];
                n[i].glbl, "boolean" == typeof t && (t = {
                    aria: t,
                    text: t
                }), "object" != typeof t && (t = {}), (t = this.opts[e] = n.extend(!0, {}, n[i].defaults[e], t)).aria && (this.bind("initAddons:after", function () {
                    this.bind("initMenu:after", function () {
                        this.trigger("initMenu:after:sr-aria")
                    }), this.bind("initNavbar:after", function () {
                        this.trigger("initNavbar:after:sr-aria", arguments[0])
                    }), this.bind("openPanel:start", function () {
                        this.trigger("openPanel:start:sr-aria", arguments[0])
                    }), this.bind("close:start", function () {
                        this.trigger("close:start:sr-aria")
                    }), this.bind("close:finish", function () {
                        this.trigger("close:finish:sr-aria")
                    }), this.bind("open:start", function () {
                        this.trigger("open:start:sr-aria")
                    }), this.bind("initOpened:after", function () {
                        this.trigger("initOpened:after:sr-aria")
                    })
                }), this.bind("updateListview", function () {
                    this.$pnls.find("." + s.listview).children().each(function () {
                        r.__sr_aria(n(this), "hidden", n(this).is("." + s.hidden))
                    })
                }), this.bind("openPanel:start", function (t) {
                    var i = this.$menu.find("." + s.panel).not(t).not(t.parents("." + s.panel)),
                        n = t.add(t.find("." + s.listitem + "_vertical ." + s.listitem + "_opened").children("." + s.panel));
                    this.__sr_aria(i, "hidden", !0), this.__sr_aria(n, "hidden", !1)
                }), this.bind("closePanel", function (t) {
                    this.__sr_aria(t, "hidden", !0)
                }), this.bind("initPanels:after", function (t) {
                    var i = t.find("." + s.btn).each(function () {
                        r.__sr_aria(n(this), "owns", n(this).attr("href").replace("#", ""))
                    });
                    this.__sr_aria(i, "haspopup", !0)
                }), this.bind("initNavbar:after", function (t) {
                    var i = t.children("." + s.navbar);
                    this.__sr_aria(i, "hidden", !t.hasClass(s.panel + "_has-navbar"))
                }), t.text && "parent" == this.opts.navbar.titleLink && this.bind("initNavbar:after", function (t) {
                    var i = t.children("." + s.navbar), n = !!i.children("." + s.btn + "_prev").length;
                    this.__sr_aria(i.children("." + s.title), "hidden", n)
                })), t.text && (this.bind("initAddons:after", function () {
                    this.bind("setPage:after", function () {
                        this.trigger("setPage:after:sr-text", arguments[0])
                    }), this.bind("initBlocker:after", function () {
                        this.trigger("initBlocker:after:sr-text")
                    })
                }), this.bind("initNavbar:after", function (t) {
                    var i = t.children("." + s.navbar), n = this.i18n(a.text.closeSubmenu);
                    i.children("." + s.btn + "_prev").html(this.__sr_text(n))
                }), this.bind("initListview:after", function (t) {
                    var i = t.data(o.parent);
                    if (i && i.length) {
                        var n = i.children("." + s.btn + "_next"),
                            e = this.i18n(a.text[n.parent().is("." + s.listitem + "_vertical") ? "toggleSubmenu" : "openSubmenu"]);
                        n.append(r.__sr_text(e))
                    }
                }))
            }, add: function () {
                s = n[i]._c, o = n[i]._d, n[i]._e, s.add("sronly")
            }, clickAnchor: function (t, i) {
            }
        }, n[i].defaults[e] = {aria: !0, text: !0}, n[i].configuration[e] = {
            text: {
                closeMenu: "Close menu",
                closeSubmenu: "Close submenu",
                openSubmenu: "Open submenu",
                toggleSubmenu: "Toggle submenu"
            }
        }, n[i].prototype.__sr_aria = function (t, i, n) {
            t.prop("aria-" + i, n)[n ? "attr" : "removeAttr"]("aria-" + i, n)
        }, n[i].prototype.__sr_role = function (t, i) {
            t.prop("role", i)[i ? "attr" : "removeAttr"]("role", i)
        }, n[i].prototype.__sr_text = function (t) {
            return '<span class="' + s.sronly + '">' + t + "</span>"
        }
    }(jQuery);
    !function (n) {
        var e, r, s, t = "mmenu", i = "scrollBugFix";
        n[t].addons[i] = {
            setup: function () {
                var o = this.opts[i];
                this.conf[i];
                s = n[t].glbl, n[t].support.touch && this.opts.offCanvas && this.opts.offCanvas.blockUI && ("boolean" == typeof o && (o = {fix: o}), "object" != typeof o && (o = {}), (o = this.opts[i] = n.extend(!0, {}, n[t].defaults[i], o)).fix && (this.bind("open:start", function () {
                    this.$pnls.children("." + e.panel + "_opened").scrollTop(0)
                }), this.bind("initMenu:after", function () {
                    this["_initWindow_" + i]()
                })))
            }, add: function () {
                e = n[t]._c, n[t]._d, r = n[t]._e
            }, clickAnchor: function (o, t) {
            }
        }, n[t].defaults[i] = {fix: !0}, n[t].prototype["_initWindow_" + i] = function () {
            var o = this;
            n(document).off(r.touchmove + "-" + i).on(r.touchmove + "-" + i, function (o) {
                s.$html.hasClass(e.wrapper + "_opened") && o.preventDefault()
            });
            var t = !1;
            s.$body.off(r.touchstart + "-" + i).on(r.touchstart + "-" + i, "." + e.panels + "> ." + e.panel, function (o) {
                s.$html.hasClass(e.wrapper + "_opened") && (t || (t = !0, 0 === o.currentTarget.scrollTop ? o.currentTarget.scrollTop = 1 : o.currentTarget.scrollHeight === o.currentTarget.scrollTop + o.currentTarget.offsetHeight && (o.currentTarget.scrollTop -= 1), t = !1))
            }).off(r.touchmove + "-" + i).on(r.touchmove + "-" + i, "." + e.panels + "> ." + e.panel, function (o) {
                s.$html.hasClass(e.wrapper + "_opened") && n(this)[0].scrollHeight > n(this).innerHeight() && o.stopPropagation()
            }), s.$wndw.off(r.orientationchange + "-" + i).on(r.orientationchange + "-" + i, function () {
                o.$pnls.children("." + e.panel + "_opened").scrollTop(0).css({"-webkit-overflow-scrolling": "auto"}).css({"-webkit-overflow-scrolling": "touch"})
            })
        }
    }(jQuery);
    return true;
}));
