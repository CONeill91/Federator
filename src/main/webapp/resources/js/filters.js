angular.module('FederatorApp.filters', []).filter('trustUrl', function($sce) {
     return function(url) {
        return $sce.trustAsResourceUrl(url);
     };
 })



.filter('highlight', function($sce) {
    return function(text, phrase) {
        if (phrase && text){
            var res = phrase.split(" ");
            var arrayLength = res.length;
            for(var i =0; i < arrayLength; i++){

                text = text.replace(new RegExp('(' + res[i] + ')', 'gi'), '<span class="highlighted">$1</span>')
            }

        }

        return $sce.trustAsHtml(text);
    };
});

