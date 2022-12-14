function SpreadViewService(vue) {
    this.readList= function(search) {

        var geoCoordMap = {
            '서울': { lng: 126.98, lat: 37.57 },
            '충북': { lng: 127.92, lat: 36.99 },
            '충남': { lng: 127.11, lat: 36.81 },
            '제주': { lng: 126.51, lat: 33.50 },
            '전북': { lng: 127.15, lat: 35.82 },
            '전남': { lng: 127.66, lat: 34.76 },
            '인천': { lng: 126.70, lat: 37.45 },
            '울산': { lng: 129.31, lat: 35.53 },
            '세종': { lng: 127.28, lat: 36.48 },
            '대전': { lng: 127.38, lat: 36.35 },
            '대구': { lng: 128.60, lat: 35.87 },
            '광주': { lng: 126.85, lat: 35.15 },
            '경북': { lng: 128.15, lat: 36.41 },
            '경남': { lng: 128.67, lat: 35.22 },
            '경기': { lng: 127.02, lat: 37.26 },
            '강원': { lng: 127.7300, lat: 37.8813 },
            '부산': { lng: 129.0756, lat: 35.1796 },
            '군산': { lng: 126.7366, lat: 35.9677 },
            '익산': { lng: 126.9576, lat: 35.9483 },
            '완주': { lng: 127.2539, lat: 35.8913 },
            '무주': { lng: 127.6608, lat: 36.0068 },
            '진안': { lng: 127.4248, lat: 35.7917 },
            '전주': { lng: 127.1480, lat: 35.8242 },
            '김제': { lng: 126.8809, lat: 35.8036 },
            '부안': { lng: 126.7335, lat: 35.7316 },
            '정읍': { lng: 126.8559, lat: 35.5699 },
            '고창': { lng: 126.7021, lat: 35.4358 },
            '순창': { lng: 127.1376, lat: 35.3744 },
            '남원': { lng: 127.3905, lat: 35.4164 },
            '장수': { lng: 127.5211, lat: 35.6473 },
            '임실': { lng: 127.2827, lat: 35.6111 },
            '진안': { lng: 127.4248, lat: 35.7917 },
        };

        // 전국
        var map_all = new kakao.maps.Map(document.getElementById('map_all'), { // 지도를 표시할 div
            center : new kakao.maps.LatLng(35.5, 127.6358), // 지도의 중심좌표
            level : 13 // 지도의 확대 레벨
        });

        // 마커 클러스터러를 생성합니다
        var clusterer_all = new kakao.maps.MarkerClusterer({
            map: map_all, // 마커들을 클러스터로 관리하고 표시할 지도 객체
            averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
            minLevel: 10, // 클러스터 할 최소 지도 레벨
            texts: getTexts
        });

        // 수시-학생부종합
        var map_jong = new kakao.maps.Map(document.getElementById('map_jong'), {
            center : new kakao.maps.LatLng(36.2683, 127.6358),
            level : 14
        });

        var clusterer_jong = new kakao.maps.MarkerClusterer({
            map: map_jong,
            averageCenter: true,
            minLevel: 10,
            texts: getTexts
        });

        // 수시-학생부종합 전북
        var map_jong_jb = new kakao.maps.Map(document.getElementById('map_jong_jb'), {
            center : new kakao.maps.LatLng(35.6111, 127),
            level : 12
        });

        var clusterer_jong_jb = new kakao.maps.MarkerClusterer({
            map: map_jong_jb,
            averageCenter: true,
            minLevel: 10,
            texts: getTexts
        });

        // 정시
        var map_jung = new kakao.maps.Map(document.getElementById('map_jung'), {
            center : new kakao.maps.LatLng(36.2683, 127.6358),
            level : 14
        });

        var clusterer_jung = new kakao.maps.MarkerClusterer({
            map: map_jung,
            averageCenter: true,
            minLevel: 10,
            texts: getTexts
        });

        // 정시 전북
        var map_jung_jb = new kakao.maps.Map(document.getElementById('map_jung_jb'), {
            center : new kakao.maps.LatLng(35.6111, 127),
            level : 12
        });

        var clusterer_jung_jb = new kakao.maps.MarkerClusterer({
            map: map_jung_jb,
            averageCenter: true,
            minLevel: 10,
            texts: getTexts
        });

        // 수시-학생부교과
        var map_gyo = new kakao.maps.Map(document.getElementById('map_gyo'), {
            center : new kakao.maps.LatLng(36.2683, 127.6358),
            level : 14
        });

        var clusterer_gyo = new kakao.maps.MarkerClusterer({
            map: map_gyo,
            averageCenter: true,
            minLevel: 10,
            texts: getTexts
        });

        // 수시-학생부교과 전북
        var map_gyo_jb = new kakao.maps.Map(document.getElementById('map_gyo_jb'), {
            center : new kakao.maps.LatLng(35.6111, 127),
            level : 12
        });

        var clusterer_gyo_jb = new kakao.maps.MarkerClusterer({
            map: map_gyo_jb,
            averageCenter: true,
            minLevel: 10,
            texts: getTexts
        });

        CommonUtil.axios()
        .post("readList", search || {})
        .then(function(response) {
            if (!response.data) return;
            console.log(response.data);

            var cntJungKey;
            var cntJungRateKey;

            var cntJongKey;
            var cntJongRateKey;

            var cntGyoKey;
            var cntGyoRateKey;

            var cntTotalAll = 0;
            var cntTotal = {};

            var thead = [];
            var tbody = [];
            var tfoot = [];

            thead.push('<tr>');
            thead.push('    <th>지역</th>');
            for (var i = 0; i < response.data.junhyungNameList.length; i++) {
                if (response.data.junhyungNameList[i] == "정시") {
                    cntJungKey = "cnt" + (i + 1);
                    cntJungRateKey = "cnt" + (i + 1) + "Rate";
                } else if (response.data.junhyungNameList[i] == "수시(학생부종합)") {
                    cntJongKey = "cnt" + (i + 1);
                    cntJongRateKey = "cnt" + (i + 1) + "Rate";
                } else if (response.data.junhyungNameList[i] == "수시(학생부교과)") {
                    cntGyoKey = "cnt" + (i + 1);
                    cntGyoRateKey = "cnt" + (i + 1) + "Rate";
                }

                thead.push('    <th>' + response.data.junhyungNameList[i] + '</th>');
            }
            thead.push('    <th>전체</th>');

            thead.push('</tr>');

            $(response.data.resultList).each(function(i,v) {
                if (v.type === 1) {
                    if (v.location && v.location !== '기타지역') {
                        var markers_all = $(new Array(v.cntAllRate).fill(geoCoordMap[v.location])).map(function(i, position) {
                            return new kakao.maps.Marker({
                                position : new kakao.maps.LatLng(position.lat, position.lng)
                            });
                        });
                        // 클러스터러에 마커들을 추가합니다
                        clusterer_all.addMarkers(markers_all);
                        console.log(clusterer_all);

                        var markers_jung = $(new Array(v[cntJungRateKey]).fill(geoCoordMap[v.location])).map(function(i, position) {
                            return new kakao.maps.Marker({
                                position : new kakao.maps.LatLng(position.lat, position.lng)
                            });
                        });
                        clusterer_jung.addMarkers(markers_jung);

                        var markers_jong = $(new Array(v[cntJongRateKey]).fill(geoCoordMap[v.location])).map(function(i, position) {
                            return new kakao.maps.Marker({
                                position : new kakao.maps.LatLng(position.lat, position.lng)
                            });
                        });
                        clusterer_jong.addMarkers(markers_jong);

                        var markers_gyo = $(new Array(v[cntGyoRateKey]).fill(geoCoordMap[v.location])).map(function(i, position) {
                            return new kakao.maps.Marker({
                                position : new kakao.maps.LatLng(position.lat, position.lng)
                            });
                        });
                        clusterer_gyo.addMarkers(markers_gyo);
                    }

                    // 표
                    tbody.push('<tr>');
                    tbody.push('    <th class="tac" style="background-color: #faedd2; white-space: nowrap;">'+v.location+'</th>');
                    for (var i = 0; i < response.data.junhyungNameList.length; i++) {
                        tbody.push('    <td class="tar">'+comma(v["cnt" + (i + 1)])+'</td>');

                        if (v["cnt" + (i + 1)] != null) {
                            if (cntTotal[response.data.junhyungNameList[i]] == null) {
                                cntTotal[response.data.junhyungNameList[i]] = 0;
                            }

                            cntTotal[response.data.junhyungNameList[i]] += v["cnt" + (i + 1)];
                        }
                    }
                    tbody.push('    <td class="tar">'+comma(v.cntAll)+'</td>');

                    cntTotalAll += v.cntAll;

                    tbody.push('</tr>');
                } else if (v.type === 2) {
                    if (v.location && v.location !== '기타지역') {
                        var markers_jong_jb = $(new Array(v[cntJongRateKey]).fill(geoCoordMap[v.location])).map(function(i, position) {
                            return new kakao.maps.Marker({
                                position : new kakao.maps.LatLng(position.lat, position.lng)
                            });
                        });
                        clusterer_jong_jb.addMarkers(markers_jong_jb);

                        var markers_jung_jb = $(new Array(v[cntJungRateKey]).fill(geoCoordMap[v.location])).map(function(i, position) {
                            return new kakao.maps.Marker({
                                position : new kakao.maps.LatLng(position.lat, position.lng)
                            });
                        });
                        clusterer_jung_jb.addMarkers(markers_jung_jb);

                        var markers_gyo_jb = $(new Array(v[cntGyoRateKey]).fill(geoCoordMap[v.location])).map(function(i, position) {
                            return new kakao.maps.Marker({
                                position : new kakao.maps.LatLng(position.lat, position.lng)
                            });
                        });
                        clusterer_gyo_jb.addMarkers(markers_gyo_jb);
                    }
                }
            });

            tfoot.push('<tr>');
            tfoot.push('    <th style="background-color: #faedd2;">합계</th>');

            for (var i = 0; i < response.data.junhyungNameList.length; i++) {
                var cnt_all = cntTotal[response.data.junhyungNameList[i]] == null ? 0 : comma(cntTotal[response.data.junhyungNameList[i]]);
                tfoot.push('    <td class="tar">' + cnt_all + '</td>');
            }
            tfoot.push('    <td class="tar">' + comma(cntTotalAll) + '</td>');
            tfoot.push('</tr>');

            $('#thead').html(thead.join("\n"));
            $('#tbody').html(tbody.join("\n"));
            $('#tfoot').html(tfoot.join("\n"));
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    }
};

function getTexts(count) {
    var num = "" + count;
    return num.slice(0, num.length-1);
}
