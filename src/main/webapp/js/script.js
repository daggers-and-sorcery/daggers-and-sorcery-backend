function loadjscssfile(filename, filetype){
    if (filetype=="js"){ //if filename is a external JavaScript file
        var fileref=document.createElement('script')
        fileref.setAttribute("type","text/javascript")
        fileref.setAttribute("src", filename)
    }
    else if (filetype=="css"){ //if filename is an external CSS file
        var fileref=document.createElement("link")
        fileref.setAttribute("rel", "stylesheet")
        fileref.setAttribute("type", "text/css")
        fileref.setAttribute("href", filename)
    }
    if (typeof fileref!="undefined")
        document.getElementsByTagName("head")[0].appendChild(fileref)
}

var Script = {
  _loadedScripts: [],
  include: function(script){
    // include script only once
    if (this._loadedScripts.include(script)){
      return false;
    }
    // request file synchronous
    var code = new Ajax.Request(script, {
      asynchronous: false, method: "GET",
      evalJS: false, evalJSON: false
    }).transport.responseText;
    // eval code on global level
    if (Prototype.Browser.IE) {
      window.execScript(code);
    } else if (Prototype.Browser.WebKit){
      $$("head").first().insert(Object.extend(
        new Element("script", {type: "text/javascript"}), {text: code}
      ));
    } else {
      window.eval(code);
    }
    // remember included script
    this._loadedScripts.push(script);
  }
};

Script.include('/js/view/view.js', 'js');
Script.include('/js/view/main/index-view.js', 'js');
Script.include('/js/main.js', 'js');