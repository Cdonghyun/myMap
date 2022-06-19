


$.ajax({
    anyne: true,
    url: '/map/BusSearch',
    type: JSON.stringify(param),
    dataType: "text",
    success: function (data){

        var gpsX = $(this).find("gpsX").text();
        var gpsY = $(this).find("gpsY").text();

        markers =new naver.maps.Marker({
            position: new naver.maps.LatLng(gpsY,gpsX),
            map: map
        });
    }
});