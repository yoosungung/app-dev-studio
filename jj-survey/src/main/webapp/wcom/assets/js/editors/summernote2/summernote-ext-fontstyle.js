(function (factory) {
  /* global define */
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module.
    define(['jquery'], factory);
  } else {
    // Browser globals: jQuery
    factory(window.jQuery);
  }
}(function ($) {
  // template, editor
  var tmpl = $.summernote.renderer.getTemplate();
  //var editor = $.summernote.eventHandler.getEditor();

  /**
   * @class plugin.fontstyle
   *
   * FontStyle Plugin
   * 
   * ### load script 
   * 
   * ```
   * < script src="plugin/summernote-ext-fontstyle.js"></script >
   * ``` 
   * 
   * ### use a plugin in toolbar
   * ```
   *    $("#editor").summernote({
   *    ...
   *    toolbar : [
   *        ['group', [ 'fontstyle' ]]
   *    ]
   *    ...    
   *    }); 
   * ```
   */
  $.summernote.addPlugin({
    /** @property {String} name name of plugin */
    name: 'fontstyle', // name of plugin
    /**
     * @property {Object} buttons 
     * @property {Function} buttons.strikethrough  
     * @property {Function} buttons.superscript   
     * @property {Function} buttons.subscript   
     * @property {Function} buttons.fontsize   dropdown button
     */
    buttons: { // buttons
      strikethrough: function (lang) {
        return tmpl.iconButton('fa fa-strikethrough', {
          event: 'strikethrough',
          title: lang.fontstyle.strikethrough
        });
      },
      superscript: function (lang) {
        return tmpl.iconButton('fa fa-superscript', {
          event: 'superscript',
          title: lang.fontstyle.superscript
        });
      },
      subscript: function (lang) {
        return tmpl.iconButton('fa fa-subscript', {
          event: 'subscript',
          title: lang.fontstyle.subscript
        });
      },
      fontsize: function (lang, options) {
        var items = options.fontSizes.reduce(function (memo, v) {
          return memo + '<li><a data-event="fontsize" href="#" data-value="' + v + '">' +
                          '<i class="fa fa-check"></i> ' + v +
                        '</a></li>';
        }, '');

        var label = '<span class="note-current-fontsize">11</span>';
        return tmpl.button(label, {
          title: lang.fontstyle.size,
          dropdown: '<ul class="dropdown-menu">' + items + '</ul>'
        });
      }
    },

    /**
     * @property {Object} events
     * @property {Function} events.strikethrough  apply strikethrough  style to selected range
     * @property {Function} events.superscript apply superscript to selected range
     * @property {Function} events.subscript apply subscript to selected range
     * @property {Function} events.fontSize apply font size to selected range
     */
    events: { // events
      strikethrough: function (event, editor, layoutInfo) {
        editor.strikethrough(layoutInfo.editable());
      },
      superscript: function (event, editor, layoutInfo) {
        editor.superscript(layoutInfo.editable());
      },
      subscript: function (event, editor, layoutInfo) {
        editor.subscript(layoutInfo.editable());
      },
      fontsize: function (event, editor, layoutInfo, value) {
        editor.fontSize(layoutInfo.editable(), value);
      }
    },

    options: {
      fontSizes: ['8', '9', '10', '11', '12', '14', '18', '24', '36']
    },

    langs: {
      'en-US': {
        fontstyle: {
          strikethrough: 'Strikethrough',
          subscript: 'Subscript',
          superscript: 'Superscript',
          size: 'Font Size'
        }
      },
      'ar-AR': {
        fontstyle: {
          strikethrough: '???? ???????? ????',
          size: '??????????'
        }
      },
      'cs-CZ': {
        fontstyle: {
          strikethrough: 'P??e??krtnut??',
          size: 'Velikost p??sma'
        }
      },
      'ca-ES': {
        fontstyle: {
          strikethrough: 'Ratllat',
          size: 'Mida de lletra'
        }
      },
      'da-DK': {
        fontstyle: {
          strikethrough: 'Gennemstreget',
          subscript: 'S??nket skrift',
          superscript: 'H??vet skrift',
          size: 'Skriftst??rrelse'
        }
      },
      'de-DE': {
        fontstyle: {
          strikethrough: 'Durchgestrichen',
          size: 'Schriftgr????e'
        }
      },
      'es-ES': {
        fontstyle: {
          strikethrough: 'Tachado',
          superscript: 'Super??ndice',
          subscript: 'Sub??ndice',
          size: 'Tama??o de la fuente'
        }
      },
      'es-EU': {
        fontstyle: {
          strikethrough: 'Marratua',
          size: 'Letren neurria'
        }
      },
      'fa-IR': {
        fontstyle: {
          strikethrough: 'Strike',
          size: '???????????? ?? ????????'
        }
      },
      'fi-FI': {
        fontstyle: {
          strikethrough: 'Yliviivaus',
          size: 'Kirjasinkoko'
        }
      },
      'fr-FR': {
        fontstyle: {
          strikethrough: 'Barr??',
          superscript: 'Exposant',
          subscript: 'Indic??',
          size: 'Taille de police'
        }
      },
      'he-IL': {
        fontstyle: {
          strikethrough: '???? ????????',
          subscript: '?????? ????????',
          superscript: '?????? ????????',
          size: '???????? ????????'
        }
      },
      'hu-HU': {
        fontstyle: {
          strikethrough: '??th??zott',
          size: 'Bet??m??ret'
        }
      },
      'id-ID': {
        fontstyle: {
          strikethrough: 'Coret',
          size: 'Ukuran font'
        }
      },
      'it-IT': {
        fontstyle: {
          strikethrough: 'Testo barrato',
          size: 'Dimensione del carattere'
        }
      },
      'jp-JP': {
        fontstyle: {
          strikethrough: '???????????????',
          size: '?????????'
        }
      },
      'ko-KR': {
        fontstyle: {
          superscript: '??? ??????',
          subscript: '?????? ??????',
          strikethrough: '?????????',
          size: '?????? ??????'
        }
      },
      'nb-NO': {
        fontstyle: {
          strikethrough: 'Gjennomstrek',
          size: 'Skriftst??rrelse'
        }
      },
      'nl-NL': {
        fontstyle: {
          strikethrough: 'Doorhalen',
          size: 'Tekstgrootte'
        }
      },
      'pl-PL': {
        fontstyle: {
          strikethrough: 'Przekre??lenie',
          size: 'Rozmiar'
        }
      },
      'pt-BR': {
        fontstyle: {
          strikethrough: 'Riscado',
          size: 'Tamanho da fonte'
        }
      },
      'ro-RO': {
        fontstyle: {
          strikethrough: 'T??iat',
          size: 'Dimensiune font'
        }
      },
      'ru-RU': {
        fontstyle: {
          strikethrough: '??????????????????????',
          subscript: '???????????? ????????????',
          superscript: '?????????????? ????????????',
          size: '???????????? ????????????'
        }
      },
      'sk-SK': {
        fontstyle: {
          strikethrough: 'Pre??krtnut??',
          size: 'Ve??kos?? p??sma'
        }
      },
      'sl-SI': {
        fontstyle: {
          strikethrough: 'Pre??rtano',
          subscript: 'Podpisano',
          superscript: 'Nadpisano',
          size: 'Velikost pisave'
        }
      },
      'sr-RS': {
        fontstyle: {
          strikethrough: '??????????????????',
          size: '???????????????? ??????????'
        }
      },
      'sr-RS-Latin': {
        fontstyle: {
          strikethrough: 'Precrtano',
          size: 'Veli??ina fonta'
        }
      },
      'sv-SE': {
        fontstyle: {
          strikethrough: 'Genomstruken',
          size: 'Teckenstorlek'
        }
      },
      'th-TH': {
        fontstyle: {
          strikethrough: '??????????????????',
          subscript: '?????????????????????',
          superscript: '???????????????',
          size: '????????????????????????????????????'
        }
      },
      'tr-TR': {
        fontstyle: {
          strikethrough: '??st?? ??izili',
          subscript: 'Subscript',
          superscript: 'Superscript',
          size: 'Yaz?? tipi boyutu'
        }
      },
      'uk-UA': {
        fontstyle: {
          strikethrough: '??????????????????????',
          subscript: '???????????? ????????????',
          superscript: '?????????????? ????????????',
          size: '???????????? ????????????'
        }
      },
      'vi-VN': {
        fontstyle: {
          strikethrough: 'G???ch Ngang',
          size: 'C??? Ch???'
        }
      },
      'zh-CN': {
        fontstyle: {
          strikethrough: '?????????',
          size: '??????'
        }
      },
      'zh-TW': {
        fontstyle: {
          strikethrough: '?????????',
          size: '????????????'
        }
      }
    }
  });
}));
