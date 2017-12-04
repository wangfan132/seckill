var seckill = {
		URL:{
			now:function() {
				return '/seckill/seckill/time/now';
			}
		},
		handleSeckillkill:function(seckillId,node) {
			node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
			$.post('/seckill/seckill/' + seckillId + '/exposer',{},function(result) {
				if(result && result['success']) {
					var exposer = result['data'];
					if(exposer['exposed']) {
						var md5 = exposer['md5'];
						var killUrl = '/seckill/seckill/' + seckillId + '/' + md5 + '/execution';
						$('#killBtn').one('click',function() {
							$(this).addClass('disabled');
							$.post(killUrl,{},function(result) {
								if(result && result['success']) {
									var killResult = result['data'];
									var state = killResult['state'];
									var stateInfo = killResult['stateInfo'];
									node.html('<span class="label label-success">' + stateInfo + '</span>');
								}
							});
						});
						node.show();
					} else {
						var now = exposer['now'];
						var start = exposer['start'];
						var end = exposer['end'];
						seckill.countdown(seckillId,now,start,end);
					}
				} else {
					console.log('result' + result);
				}
			},'json');
		},
		validatePhone : function(phone) {
			if(phone && phone.length == 11 && !isNaN(phone)) {
				return true;
			} else {
				return false;
			}
		},
		countdown:function(seckillId,nowTime,startTime,endTime) {
			var seckillBox = $('#seckill-box');
			if(nowTime > endTime) {
				seckillBox.html('秒杀结束!');
			} else if(nowTime < startTime) {
				var killTime = new Date(startTime + 1000);
				seckillBox.countdown(killTime,function(event) {
					var format = event.strftime('秒杀计时: %D天 %H时 %M分 %S秒');
					seckillBox.html(format);
				}).on('finish.countdown',function() {
					seckill.handleSeckillkill(seckillId,seckillBox);
				});
			} else {
				seckill.handleSeckillkill(seckillId,seckillBox);
			}
		},
		detail:{
			init:function(params) {
				var killPhone = $.cookie('killPhone');
				var startTime = params['startTime'];
				var endTime = params['endTime'];
				var seckillId = params['seckillId'];
				if(!seckill.validatePhone(killPhone)) {
					var killPhoneModal = $('#killPhoneModal');
					killPhoneModal.modal({
						show:true,
						backdrop:'static',
						keyboard:false
					});
					$('#killPhoneBtn').click(function(){
						var inputPhone = $('#killPhoneKey').val();
						if(seckill.validatePhone(inputPhone)) {
							$.cookie('killPhone',inputPhone,{ expires: 7 });
							window.location.reload();
						} else {
							$('#killPhoneMessage').hide().html('<label class="label label-danger>手机号错误</label>').show(300);
						}
					});
				}
				$.get('/seckill/seckill/time/now',{},function(result) {
					if(result && result['success']) {
						var nowTime = result['data'];
						seckill.countdown(seckillId,nowTime,startTime,endTime);
					} else {
						console.log('result:' + result);
					}
				},"json");
			}
		}
}