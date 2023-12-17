import * as board from './board.js';
//import * as product from './product.js';

$(window).on('load', function(e) {
	alert('load  event:' + e);
});

board.init();
//product.init();