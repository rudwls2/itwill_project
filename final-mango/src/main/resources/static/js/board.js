///////////////////////notification///////////////////////////////

$(document).ready(function() {
	var initialData = $('#notificationContainer').html();
	var notilistContainer = $('#notificationContainer');

	function filterItems(filter) {
		notilistContainer.empty();
		var itemList = $(initialData).find('.blog-item-style-1, .blog-item-style-3').toArray();
		$.each(itemList, function(index, item) {
			var notiType = $(item).find('.time').eq(0).text();

			if (filter === 'all' ||
				(filter === 'service' && notiType === '서비스공지') ||
				(filter === 'etc' && notiType === '기타') ||
				(filter === 'system' && notiType === '시스템공지') ||
				(filter === 'normal' && notiType === '일반공지')) {
				notilistContainer.append(item);
			}
		});
	}



	$('#normalCategory').on('click', function() {
		filterItems('normal');
	});
	$('#otherCategory').on('click', function() {
		filterItems('etc');
	});
	$('#serviceCategory').on('click', function() {
		filterItems('service');
	});
	$('#systemCategory').on('click', function() {
		filterItems('system');
	});
	$('#all').on('click', function() {
		filterItems('all');
	});

	filterItems('all');
});


document.addEventListener('DOMContentLoaded', function() {
	const searchInput = document.getElementById('searchInput');
	const notilistContainer = document.getElementById('notificationContainer');
	var initialData = $('#notificationContainer').html();

	searchInput.addEventListener('keydown', function(event) {
		if (event.key === 'Enter') {
			const searchValue = searchInput.value;

			// 여기에서 검색어를 사용하여 필터링된 항목을 표시하거나 처리할 수 있습니다
			filterItems(searchValue);
			searchInput.value = ''; // 검색어 필드 비우기

		}
	});

	var itemList = $(initialData).find('.blog-item-style-1, .blog-item-style-3').toArray();

	function filterItems(searchQuery) {
		notilistContainer.innerHTML = ''; // 리스트 비우기


		itemList.forEach(function(item) {
			var titleElement = item.getElementsByTagName('a')[0]; // a 요소 선택
			var title = titleElement.textContent;

			if (title.toLowerCase().includes(searchQuery.toLowerCase())) {
				notilistContainer.appendChild(item);
			}
		});


	}
});
///////////////////////faq///////////////////////////////
$(document).ready(function() {
	var initialData = $('#faqContainer').html();
	var faqlistContainer = $('#faqContainer');
	function filterItems(filter) {
		faqlistContainer.empty();
		var itemList = $(initialData).find('.blog-item-style-1, .blog-item-style-3').toArray();
		$.each(itemList, function(index, item) {
			var notiType = $(item).find('.time').eq(0).text();
			if (filter === 'all' ||
				(filter === 'user' && notiType === '회원정보') ||
				(filter === 'order' && notiType === '결제/환불') ||
				(filter === 'event' && notiType === '이벤트') ||
				(filter === 'etc' && notiType === '기타')) {
				faqlistContainer.append(item);
			}
		});
	}
	$('#userCategory').on('click', function() {
		filterItems('user');
	});
	$('#orderCategory').on('click', function() {
		filterItems('order');
	});
	$('#eventCategory').on('click', function() {
		filterItems('event');
	});
	$('#otherCategory').on('click', function() {
		filterItems('etc');
	});
	$('#all').on('click', function() {
		filterItems('all');
	});

	filterItems('all');
});

//검색기능

document.addEventListener('DOMContentLoaded', function() {
	const searchInput = document.getElementById('searchInput');
	const faqContainer = document.getElementById('faqContainer');
	var initialData = $('#faqContainer').html();

	searchInput.addEventListener('keydown', function(event) {
		if (event.key === 'Enter') {
			const searchValue = searchInput.value;

			filterItems(searchValue);
			searchInput.value = ''; // 검색어 필드 비우기

		}
	});

	var itemList = $(initialData).find('.blog-item-style-1, .blog-item-style-3').toArray();
	console.log(itemList);

	function filterItems(searchQuery) {
		faqContainer.innerHTML = ''; // 리스트 비우기


		itemList.forEach(function(item) {
			var titleElement = item.getElementsByTagName('a')[0]; // a 요소 선택
			var title = titleElement.textContent;
			console.log(titleElement);

			if (title.toLowerCase().includes(searchQuery.toLowerCase())) {
				faqContainer.appendChild(item);
			}
		});


	}
});

///////////////////////magazine///////////////////////////////

document.addEventListener('DOMContentLoaded', function() {
	const searchInput = document.getElementById('searchInput');
	var titleElements = document.querySelectorAll('.blog-item-style-2 h3 a');
	var magazineContainer = document.getElementById('magazineContainer');

	searchInput.addEventListener('keydown', function(event) {
		if (event.key === 'Enter') {
			const searchValue = searchInput.value;

			filterItems(searchValue);
			searchInput.value = ''; // 검색어 필드 비우기
		}
	});

	function filterItems(searchQuery) {
		magazineContainer.innerHTML = ''; // 리스트 비우기

		titleElements.forEach(function(titleElement, index) {
			var title = titleElement.textContent;
			console.log("titleElement->" + titleElement);
			if (title.toLowerCase().includes(searchQuery.toLowerCase())) {
				var item = titleElement.closest('.col-md-4'); // 부모 .col-md-4 요소 선택
				magazineContainer.appendChild(item);
			}
		});
	}
});



///////////////////////inquiries///////////////////////////////


$(document).ready(function() {
	var initialData = $('#inquiriesContainer').html(); //페이지 시작할때 타임리프로 구현한 데이터 저장
	var sortSelect = $('#sortSelect');
	var inquiriesContainer = $('#inquiriesContainer');

	function sortItems(ascending) { //정렬
		var itemList = inquiriesContainer.find('.ceb-item-style-2').toArray();
		itemList.sort(function(a, b) {
			var dateA = new Date($(a).find('p').eq(0).text());
			var dateB = new Date($(b).find('p').eq(0).text());
			return ascending ? dateA - dateB : dateB - dateA;
		});
		inquiriesContainer.empty();
		$.each(itemList, function(index, item) {
			inquiriesContainer.append(item);
		});
	}

	sortSelect.on('change', function() {
		var selectedOption = sortSelect.val();
		if (selectedOption === 'latest') {
			sortItems(false); // 최신순으로 정렬
		} else if (selectedOption === 'oldest') {
			sortItems(true); // 오래된순으로 정렬
		}
	});

});

document.addEventListener('DOMContentLoaded', function() {
	const searchInput = document.getElementById('searchInput');
	var titleElements = document.querySelectorAll('.ceb-item-style-2 h2 a');
	var inquiriesContainer = document.getElementById('inquiriesContainer');

	searchInput.addEventListener('keydown', function(event) {
		if (event.key === 'Enter') {
			const searchValue = searchInput.value;

			filterItems(searchValue);
			searchInput.value = ''; // 검색어 필드 비우기
		}
	});

	function filterItems(searchQuery) {
		inquiriesContainer.innerHTML = ''; // 리스트 비우기

		titleElements.forEach(function(titleElement, index) {
			var title = titleElement.textContent;
			console.log("titleElement->" + titleElement);
			if (title.toLowerCase().includes(searchQuery.toLowerCase())) {
				var item = titleElement.closest('.ceb-item-style-2'); // 부모 .ceb-item-style-2 요소 선택
				inquiriesContainer.appendChild(item);
			}
		});
	}
});





///////////////////////event///////////////////////////////

$(document).ready(function() {
	var initialData = $('#eventContainer').html();
	var movielistContainer = $('#eventContainer');

	function filterItems(filter) {
		movielistContainer.empty();
		var itemList = $(initialData).find('.movie-item-style-2').toArray();
		$.each(itemList, function(index, item) {
			var eventType = $(item).find('.run-time').text();

			if (filter === 'all' || (filter === 'end' && eventType === '종료된이벤트') || (filter === 'ing' && eventType === '진행중이벤트')) {
				movielistContainer.append(item);
			}
		});
	}
	$('#choiceSelect').on('change', function() {
		var selectedOption = $(this).val();
		filterItems(selectedOption);
	});
	filterItems('all');
});


////////////////////////write//////////////////////////////



