
    var markers = [];
    let presentPosition;
    var mapOptions = {
    center: new naver.maps.LatLng(37.566826, 126.9786567),
    zoom: 15,
    zoomControl : true,
    zoomControlOptions: {
    position: naver.maps.Position.TOP_RIGHT
}

};
    var map = new naver.maps.Map('map', mapOptions); // id와 option 지도 생성

    if (navigator.geolocation) {

    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {

        let lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도

        var locPosition = new naver.maps.LatLng(lat, lon) // geolocation으로 얻어온 좌표
        presentPosition=locPosition;
        map.setCenter(locPosition);

        new naver.maps.Marker({
            map: map,
            position: locPosition
        });

    });

} else { // HTML5의 GeoLocation을 사용할 수 없을때

    naver.maps.LatLng(37.566826, 126.9786567) //var locPosition = new
    alert('현재 위치를 찾을 수 없습니다!');
}





    // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
    var infoWindow = new naver.maps.InfoWindow({zIndex:1});
    var ps = new kakao.maps.services.Places();
    window.onload = function (){
    const searchForm = document.querySelector('.form');
    searchForm.addEventListener('submit', function(e){
    e.preventDefault();
    // 키워드로 장소를 검색합니다
    searchPlaces();
})
}

    // 키워드 검색을 요청하는 함수입니다
    function searchPlaces() {

    var keyword = document.getElementById('keyword').value;

    if (!keyword.replace(/^\s+|\s+$/g, '')) {!
    alert('키워드를 입력해주세요!');
    return false;
}

    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch( keyword, placesSearchCB);
}

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

    // 정상적으로 검색이 완료됐으면
    // 검색 목록과 마커를 표출합니다
    displayPlaces(data); // naver 사용

    // 페이지 번호를 표출합니다
    displayPagination(pagination); // no

} else if (status === kakao.maps.services.Status.ZERO_RESULT) {

    alert('검색 결과가 존재하지 않습니다.');
    return;

} else if (status === kakao.maps.services.Status.ERROR) {

    alert('검색 결과 중 오류가 발생했습니다.');
    return;

}
}

    // 검색 결과 목록과 마커를 표출하는 함수입니다
    function displayPlaces(places) {

    var listEl = document.getElementById('placesList'),
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(),
    bounds = new naver.maps.LatLngBounds(),
    listStr = '';

    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();

    for ( var i=0; i<places.length; i++ ) {

    const lon = places[i].x;
    const lat = places[i].y;

    // 마커를 생성하고 지도에 표시합니다
    var placePosition = new naver.maps.LatLng(lat, lon),
    marker = addMarker(placePosition, i),
    itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
    // LatLngBounds 객체에 좌표를 추가합니다
    bounds.extend(placePosition);

    // 마커와 검색결과 항목에 mouseover 했을때
    // 해당 장소에 인포윈도우에 장소명을 표시합니다
    // mouseout 했을 때는 인포윈도우를 닫습니다
    (function(marker, title) {
    naver.maps.Event.addListener(marker, 'mouseover', function() {
    displayInfoWindow(marker, title);

});

    naver.maps.Event.addListener(marker, 'mouseout', function() {
    infoWindow.close();
});

    itemEl.onmouseover =  function () {
    displayInfoWindow(marker, title);
};

    itemEl.onmouseout =  function () {
    infoWindow.close();
};
})(marker, places[i].place_name);

    // 마커와 검색 결과를 클릭했을때 좌표를 가져온다
    (function(marker, title) {
    kakao.maps.event.addListener(marker, 'click', function() {
    searchDetailAddFromCoords(presentPosition, function(result, status) {
    if (status === kakao.maps.services.Status.OK) {
    detailAddr = !!result[0].road_address ? result[0].road_address.address_name : result[0].address.address_name;
    location.href = "https://map.naver.com/?sName="+detailAddr+"&eName="+title

}
});
})

    itemEl.onclick =  function () {
    searchDetailAddFromCoords(presentPosition, function(result, status) {
    if (status === kakao.maps.services.Status.OK) {
    detailAddr = !!result[0].road_address ? result[0].road_address.address_name : result[0].address.address_name;
    location.href = "https://map.naver.com/?sName="+detailAddr+"&eName="+title
}
});
};
})(marker, places[i].place_name);

    fragment.appendChild(itemEl);
}

    // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.panToBounds(bounds);
}

    // 검색결과 항목을 Element로 반환하는 함수입니다
    function getListItem(index, places) {

    var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
    '<div class="info">' +
    '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
    itemStr += '    <span>' + places.road_address_name + '</span>' +
    '   <span class="jibun gray">' +  places.address_name  + '</span>';
} else {
    itemStr += '    <span>' +  places.address_name  + '</span>';
}

    itemStr += '  <span class="tel">' + places.phone  + '</span>' +
    '</div>';

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    function addMarker(position, idx) {
    var
    marker = new naver.maps.Marker({
    position: position, // 마커의 위치
    map: map,
    icon: {
    url: 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png',
    size : new naver.maps.Size(36, 36), // 스프라이트 이미지의 크기
    origin : new naver.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
    anchor: new naver.maps.Point(13, 37)
}
});

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
    markers[i].setMap(null);
}
    markers = [];
}

    // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
    function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
    fragment = document.createDocumentFragment(),
    i;

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
    paginationEl.removeChild (paginationEl.lastChild);
}

    for (i=1; i<=pagination.last; i++) {
    var el = document.createElement('a');
    el.href = "#";
    el.innerHTML = i;

    if (i===pagination.current) {
    el.className = 'on';
} else {
    el.onclick = (function(i) {
    return function() {
    pagination.gotoPage(i);
}
})(i);
}

    fragment.appendChild(el);
}
    paginationEl.appendChild(fragment);
}

    // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
    // 인포윈도우에 장소명을 표시합니다
    function displayInfoWindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infoWindow.setContent(content);
    infoWindow.open(map, marker);



}

    // 검색결과 목록의 자식 Element를 제거하는 함수입니다
    function removeAllChildNods(el) {
    while (el.hasChildNodes()) {
    el.removeChild (el.lastChild);
}
}

$.ajax({
    url: '/information',
    type: "GET",
    dataType: "text",
    success: function (data){

        var tmX = $(this).find("tmX").text();
        var tmY = $(this).find("tmY").text();

        markers =new naver.maps.Marker({
            position: new naver.maps.LatLng(tmY,tmX),
            map: map
        });
    }
});





    // 좌표 -> 주소
    var geocoder = new kakao.maps.services.Geocoder();
    function searchDetailAddFromCoords(coords, callback) {
    // geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}