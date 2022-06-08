// const express = require('express');
// const router = express.Router();
//API 키
const key = "PFJo24LCvJzZzwvBaJscmSUs%2BRCK7VhdXDqP754kX2LM4c9%2B%2BPIulcBYh2m1A1POKCHhdzUouN4Nn26pFML47A%3D%3D"

//버스위치 정보
const stationURL = 'http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid';
//대중교통 환승 정보
const transferURL = 'http://ws.bus.go.kr/api/rest/pathinfo/getLocationInfo';
//정류소 정보
const stopURL = 'http://ws.bus.go.kr/api/rest/stationinfo/getStationByName';
//노선 정보
const routeURL = 'http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?serviceKey=';
//버스 도착 정보
const arrivalURL = 'http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRouteAll';

let routeId;

    //검색값 가져오기
    function getNum(){
        const getId = document.getElementById("busID").value;
        const url = routeURL + key + "&" + "strSrch=" + getId;
        console.log(url);

        if(DataTransfer.hasOwnProperty("msgBody")) {
            routeId = data.ServiceResult.msgBody[0].itemList[0].busRouteId[0];
            callback(routId);
            console.log(routeId);
        }
        else{
            callback(null);
        }

    }
