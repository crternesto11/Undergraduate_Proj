//三级（市，区县，街道）直辖市，除此之外的省都是四级（省，市，区县，街道）
		const municipality = ["北京市","天津市","上海市","重庆市","香港特别行政区","澳门特别行政区"];
		//最小级zoom，省级zoom
		const ZOOM_PROVINCE_LEVEL = 5;
		//市级zoom
		const ZOOM_CITY_LEVEL = 8;
		//区县级zoom
		const ZOOM_DISTRICT_LEVEL = 10;
		//街道级zoom
		const ZOOM_STREET_LEVEL = 12;
		//缩放最大级别 zoom
		const ZOOM_MAX_LEVEL = 17;
		//当前zoom级别
		let current_zoom = ZOOM_PROVINCE_LEVEL;

		//省市区县级别
		const LEVEL_PROVINCE = 1;
		const LEVEL_CITY = 2;
		const LEVEL_DISTRICT = 3;
		const LEVEL_STREET = 4;

		//事件控制变量
		let marker_mouseover_flag = true;

		//清除覆盖物
		function removeOverlay(map) {
    		map.clearOverlays();
		}

		//设置所有覆盖物为可清除
		function setAllOverLayClear(map){
			let allOverlay = map.getOverlays();
			for(let overlay of allOverlay){
				overlay.enableMassClear();
			}
		}

/**
* 添加行政区边框
* @param map
* @param args 行政区域名称数组，以百度地图标准行政区域名称为主
* @param isAlwaysShow 是否持续显示，默认为false
* @param strokeColor 边框线条颜色，填入颜色编码，默认为#ff9a39
* @param fillColor 覆盖物背景色，填入颜色编码，默认为无色透明
*/
		function getBoundaryAndColor(map, arg,isAlwaysShow,fillColor,strokeWeight,strokeColor){
			strokeColor = strokeColor || "#ff4700";
            if(fillColor==null)
			 {
                fillColor = "#61dcff";
             }else{
                fillColor = "#F2ffffff";
             }
			isAlwaysShow = isAlwaysShow || false;
			strokeWeight = strokeWeight || 1;
			//通过行政区域名称获取行政区划
			let bdary = new BMapGL.Boundary();
			bdary.get(arg,function(rs){
				let count = rs.boundaries.length;
				if (count === 0){
					return;
				}
				for(var i=0;i<count;i++){
					let ply = new BMapGL.Polygon(rs.boundaries[i],{
						strokeColor: strokeColor,
						fillColor: fillColor,
						strokeWeight:strokeWeight
					});
					map.addOverlay(ply);
                    if(isAlwaysShow){
                        ply.disableMassClear();
                    }
				}
				$("#container").mLoading("hide");
				marker_mouseover_flag = true;
			})
		}

/**
 * 渲染标记和标签(重点方法)
 * @param map map地图对象
 * @param level 地图级别 1：省级 2：市级 3：区县级
 * @param data json数据
 * @param markerClickCallback 标记点击事件方法回调函数
 * @param center 中心坐标
 */
 		function renderMarkersAndLabels(map, level, data, markerClickCallback, center, isClick){
			//设置所有覆盖物均可清除
			setAllOverLayClear(map);
			//清除所有覆盖物
			removeOverlay(map);
			//点击时需要设置zoom级别与定位中心，并将current_zoom置为当前zoom
			if(level != LEVEL_PROVINCE && center){
				if(level == LEVEL_CITY && isClick){
                    map.centerAndZoom(center, ZOOM_CITY_LEVEL);
					current_zoom = ZOOM_CITY_LEVEL;
				}else if(level == LEVEL_DISTRICT && isClick){
					map.centerAndZoom(center, ZOOM_DISTRICT_LEVEL);
					current_zoom = ZOOM_DISTRICT_LEVEL;
			    }else if(level == LEVEL_STREET && isClick){
					map.centerAndZoom(center, ZOOM_STREET_LEVEL);
					current_zoom = ZOOM_STREET_LEVEL;
				}
			}
			//1,2,3级地图省略道路，4级地图展示道路
			if(level == LEVEL_STREET){
				map.setMapStyleV2({});
			}else{
				map.setMapStyleV2({
					styleJson:[{
						"featureType": "road",
						"elementType": "all",
						"stylers":{
							"color": "#ffffff",
							"visibility": "off"
						}
					}]
				})
			}

			for (let i = 0; i < data.length; i++){
				let d = data[i];
				if (d.lon&d.lat){
					let x = d.lon;
					let y = d.lat;
						//标记显示文本
						let labelContent;
						//悬停信息框展示文本
						let windowInfoContent;
						//获取图片的序号 对应m0-m9.png
						let img_num;
						//图片的偏移位置大小
						let size;
						
						if (level == LEVEL_PROVINCE){
							windowInfoContent = "省份：" + d.provinceName + "<br>排名：" + d.rank + "<br>省销售额：" + d.sale + "<br>省门店数量：" + d.shopNum;
							labelContent = d.shopNum;
							//3.6保证35个省能分布到0-9的渐进图片里，每张图有两个级别，偏移位置根据图片大小而定的
							img_num = Math.floor(d.rank/3.6);
							size = new BMapGL.Size(-10 - ((10 - img_num) * 0.6),-4 + (10 - img_num) * 0.2);
						}else if(level == LEVEL_CITY){
							windowInfoContent = "城市：" + d.cityName + "<br>排名：" + d.rank + "<br>市销售额：" + d.sale + "<br>市门店数量：" + d.shopNum;
							
							labelContent = d.shopNum;
							//2.2保证15个市能分布到0-7的渐进图片里，每张图有两个级别，偏移位置根据图片大小而定的
							img_num = Math.floor(d.rank/2.2);
							size = new BMapGL.Size(-8- ((10 - img_num) * 0.4),-4 + (10 - img_num) * 0.2);
						}else if(level == LEVEL_DISTRICT){
							windowInfoContent = "区县：" + d.districtName + "<br>排名：" + d.rank + "<br>区县销售额：" + d.sale + "<br>区县门店数量：" + d.shopNum;
							
							labelContent = d.shopNum;
							//2.2保证15个区县能分布到0-7的渐进图片里，每张图有两个级别，偏移位置根据图片大小而定的
							img_num = Math.floor(d.rank/2.2);
							size = new BMapGL.Size(-6- ((10 - img_num) * 0.4),-5 + (10 - img_num) * 0.2);
						}else if (level == LEVEL_STREET){
							
							windowInfoContent = "门店名称：" + d.shopName + "<br>销售额：" + d.shopSale;
							labelContent = d.shopName;
						}
						let marker;
						let label;
						var markerPoint = new BMapGL.Point(x,y);
						//1,2,3级地图选用自定义marker，4级地图选用默认地图，且label也不一样
						if(level != LEVEL_STREET){
							//创建自定义icon以及自定义icon大小
							let myIcon = new BMapGL.Icon("/images/m"+ img_num + ".png", new BMapGL.Size(50+(10-img_num)*2,50+(10-img_num)*2));
							marker = new BMapGL.Marker(markerPoint,{icon:myIcon});
							
							let opts ={
								position: markerPoint, // 指定文本标注所在的地理位置
								offset:size
							};
							label = new BMapGL.Label(labelContent,opts);
							label.setStyle({
								color: "white",
                        		fontSize: "4px",
                        		height: "auto",
                        		lineHeight: "6px",
                        		fontFamily: "微软雅黑",
                        		backgroundColor: 'none',
                        		maxWidth: 'none',
                        		border: 'none',
								'font-weight':'bold'
							});
						}else{
							let myIcon = new BMapGL.Icon("../images/ditu.png",new BMapGL.Size(32,32));
							marker = new BMapGL.Marker(markerPoint,{icon:myIcon});
							let opts = {
								position: markerPoint, // 指定文本标注所在的地理位置
								offset:size
							};
							label = new BMapGL.Label(labelContent,opts);
							label.setStyle({
								color: "black",
                        		fontSize: "12px",
                        		height: "auto",
                        		lineHeight: "15px",
                        		fontFamily: "微软雅黑",
                        		backgroundColor: 'white',
                        		maxWidth: 'none'
							});
						}
						
						//禁止覆盖物在map.clearOverlays方法中被清除，与行政区划覆盖物区别
						marker.disableMassClear();
						label.disableMassClear();
						
						//注册标记鼠标单点事件
						marker.addEventListener('click',function(e){
							if(marker_mouseover_flag){
								marker_mouseover_flag = false;
								removeOverlay(map);
								setTimeout(function(){
									label.setContent(labelContent);
									let opts = {
										width : 50,     // 信息窗口宽度
										height: 100,     // 信息窗口高度
										offset : new BMapGL.Size(20,-30), //信息窗口偏移
									};
									// 创建信息窗口对象
									let infoWindow = new BMapGL.InfoWindow(windowInfoContent, opts);
									//开启信息窗口
									map.openInfoWindow(infoWindow,e.target.latLng);
									if(level == LEVEL_PROVINCE){
										getBoundaryAndColor(map,d.provinceName);
									}else if(level == LEVEL_CITY){
										getBoundaryAndColor(map,d.cityName);
									}else if(level == LEVEL_DISTRICT){
										getBoundaryAndColor(map,d.districtName);
									}
									marker_mouseover_flag = true;
								},10)
							}
						});

						marker.addEventListener('mouseout',function(){
							if(marker_mouseover_flag){
								removeOverlay(map);
							}
						});

						//初始化标记点击事件
						if (markerClickCallback){  //调用点击事件，可以运用于传参
							markerClickCallback(d,map, marker,level);
						}
						map.addOverlay(label);
						map.addOverlay(marker);
				}
			}
 		}

/**
 * 设置区县级标记双击事件
 * @param marker 标记对象
 * @param markerLevel 标记所在的地图级别
 */
 		function bindMarkersEvent(data,map, marker, markerLevel){
			marker.addEventListener('dblclick',function(){
				let _this = $(this);
				let center = new BMapGL.Point(_this[0].latLng["lng"],_this[0].latLng["lat"]);
				//显示loading组件
				$("#container").mLoading("show");
				if(markerLevel == LEVEL_PROVINCE){
					renderLevelTwoMap(data.warzoneID,map,center,true);
					window.test(data,markerLevel)
				}else if(markerLevel == LEVEL_CITY){
					renderLevelThreeMap(data.cityID,map,center,true);
					window.test(data,markerLevel)
				}else if(markerLevel == LEVEL_DISTRICT){
					renderLevelFourMap(data.minimarketID,map,center,true);
					window.test(data,markerLevel)
				}else{
					window.test(data,markerLevel)
					window.show_dialog(data)
				}
			})
		}

/**
 * 获得所有省份信息并渲染省级级（一级）地图
 * @param map map地图对象
 */
		function renderLevelOneMap(map){
			$.ajax({
            	url: "http://localhost:8080/baidu_map/pro_data",
				//url:"../ProvincesList.json",
            	type: "GET",
            	dataType: "json",
				contentType: "application/x-www-form-urlencoded",
            	success: function (data) {
                	renderMarkersAndLabels(map , LEVEL_PROVINCE , data.data , bindMarkersEvent);
            	}
        	});
		}

//逆向解析省级坐标并渲染该省的市级（二级）地图
		function renderLevelTwoMap(warzoneID,map,center,isClick){
			center = center //|| map.getCenter();
			isClick = isClick || false;
			let geoc = new BMapGL.Geocoder();
			geoc.getLocation(center,function(rs){
				if(rs==null){
					return;
				}
				//获得省份
				province = rs.addressComponents.province;
				//判断是否属于直辖市
				//if($.inArray(province , municipality) == -1){
					//调用后台接口获取城市数据cityData
                        $.ajax({
                            url:"http://localhost:8080/baidu_map/city_data",
                            type:"GET",
                            dataType:"JSON",
							data : {WarzoneID:warzoneID},
							contentType: "application/x-www-form-urlencoded",
                            success:function(Data){
                                renderMarkersAndLabels(map, LEVEL_CITY, Data.data, bindMarkersEvent,center,isClick);
                                getBoundaryAndColor(map,province,true,"",2);
                            }
                        })
				//}else {
					//获得市级
					/*if(rs==null){
						return;
					}
					city = rs.addressComponents.city;
					//调用后台接口获取区县数据DistrictData
					$.ajax({
						url:"http://localhost:8080/baidu_map/minimarket_data",
						type:"GET",
						dataType:"JSON",
						data:{CityID:warzoneID},
						contentType: "application/x-www-form-urlencoded",
						success:function(DistrictData){
							renderMarkersAndLabels(map, LEVEL_DISTRICT, DistrictData.data, bindMarkersEvent,center,isClick);
							getBoundaryAndColor(map,city,true,"",2);
						}
					})
				}*/
			})
		}

//逆向解析市级坐标并渲染该市的区县级（三级）地图
		function renderLevelThreeMap(cityID,map,center,isClick){
			center = center    //|| map.getCenter;
			isClick = isClick || false;
			let geoc = new BMapGL.Geocoder();
			geoc.getLocation(center,function(rs){
				//获得市级
				if(rs==null){
					return;
				}
				city = rs.addressComponents.city;
				//调用后台接口获取区县数据DistrictData
				$.ajax({
					url:"http://localhost:8080/baidu_map/minimarket_data",
                    type:"GET",
                    dataType:"JSON",
					data:{CityID:cityID},
					contentType: "application/x-www-form-urlencoded",
                    success:function(DistrictData){
						renderMarkersAndLabels(map, LEVEL_DISTRICT, DistrictData.data, bindMarkersEvent,center,isClick);
						getBoundaryAndColor(map,city,true,"",2);
					}
				})	
			});
		}

//逆向解析区县级坐标并渲染该区县的乡镇街道级（四级）地图
		function renderLevelFourMap(minimarketID,map,center,isClick){
			center = center    //|| map.getCenter();
			isClick = isClick || false;
			let geoc = new BMapGL.Geocoder();
			geoc.getLocation(center,function(rs){
				//获得区县
				if(rs==null){
					return;
				}
				district = rs.addressComponents.district;
				//调用后台接口获取乡镇街道数据streetData
				$.ajax({
					url:"http://localhost:8080/baidu_map/store_data",
					type:"GET",
					dataType:"JSON",
					data:{MinimarketID:minimarketID},
					contentType: "application/x-www-form-urlencoded",
					success:function(Data){
						renderMarkersAndLabels(map, LEVEL_STREET, Data.data, bindMarkersEvent,center,isClick);
						getBoundaryAndColor(map,district,true,"",2);
					}
				})				
			})
		}
/**
* zoom切换监听事件
* @param map map地图对象
*/
		 function bindZoomSwithListener(map){
			let flag = true;
			const scrollFunc = (e)=>{
				if(flag){
					flag = false;
					setTimeout(function (){
						//是否放大
						let isUp = false;
						//IE/Opera/Chrome 的滚轮判断为wheelDelta = +- 120 ，firefox的滚轮判断为detail = +- 3
						//+120为放大，-120为缩小 -3为放大，+3为缩小
						if(e.wheelDelta){
							if(e.wheelDelta == 120){
								isUp = true;
                        		map.zoomIn();
							}else{
                        		map.zoomOut();
                    		}
						}else if(e.detail){  //Firefox
							if(e.detail == -3){
                        		isUp = true;
                        		map.zoomIn();
                    		}
                    		else{
                        		map.zoomOut();
                    		}
						}
						//从一级跳二级
						if(isUp && current_zoom == ZOOM_CITY_LEVEL - 1){
							renderLevelTwoMap(map);
						}
						//从二级跳一级
						else if(!isUp && current_zoom == ZOOM_CITY_LEVEL){
							renderLevelOneMap(map);
						}
						//从二级跳三级
						else if(isUp && current_zoom == ZOOM_DISTRICT_LEVEL - 1){
							renderLevelThreeMap(map);
						}
						//从三级跳二级
						else if(!isUp && current_zoom == ZOOM_DISTRICT_LEVEL){
							renderLevelTwoMap(map);
						}
						//从三级跳四级
						else if(isUp && current_zoom == ZOOM_STREET_LEVEL - 1){
							renderLevelFourMap(map);
						}
						//从四级跳三级
						else if(!isUp && current_zoom == ZOOM_STREET_LEVEL){
							renderLevelThreeMap(map);
						}
						else{
                    		map.removeOverlay();
                		}
					})
				}
			};

			/*注册事件*/
			let userAgent = navigator.userAgent;
			let isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
			if(isFF){
				document.addEventListener('DOMMouseScroll',scrollFunc,false);
			}else{
				window.onmousewheel=document.onmousewheel=scrollFunc;//IE/Opera/Chrome
			}
		 }

/**
* 鼠标拖动地图触发事件
* @param map
*/
		 function bindProvinceAndCitySwitchListener(map){
			let mouseFlag = true;
			map.addEventListener('dragend',function(){
				if(mouseFlag){
					mouseFlag = false;
					setTimeout(function(){
						let zoom = map.getZoom();
						//市级
						if(zoom >= ZOOM_CITY_LEVEL && zoom < ZOOM_DISTRICT_LEVEL){
							$("#container").mLoading("show");
							renderLevelTwoMap(map);
						}
						//区县级
						else if(zoom >= ZOOM_DISTRICT_LEVEL && zoom < ZOOM_STREET_LEVEL){
							$("#container").mLoading("show");
							renderLevelThreeMap(map);
						}
						mouseFlag = true;
					},2000)
				}
			});
		 }		