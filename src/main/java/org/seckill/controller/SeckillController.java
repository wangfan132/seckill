package org.seckill.controller;

import java.util.Date;
import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SeckillService seckillService;
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> list = seckillService.getSeckillList();
		model.addAttribute("list", list);
		return "list";
	}
	@RequestMapping(value="/{seckillId}/detail",method=RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId,Model model) {
		if(seckillId == null) {
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getById(seckillId);
		if(seckill == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}
	@RequestMapping(value="/{seckillId}/exposer",method=RequestMethod.POST,produces= {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
		SeckillResult<Exposer> seckillResult = null;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			seckillResult = new SeckillResult<Exposer>(true,exposer);
		} catch(Exception e) {
			logger.error(e.getMessage(),e);
			seckillResult = new SeckillResult<Exposer>(false,e.getMessage());
		}
		return seckillResult;
	}
	@RequestMapping(value="/{seckillId}/{md5}/execution",method=RequestMethod.POST,produces= {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,@PathVariable("md5") String md5,@CookieValue(value="killPhone",required=false) String phone) {
		SeckillResult<SeckillExecution> seckillResult = null;
		if(phone == null) {
			seckillResult = new SeckillResult<SeckillExecution>(false,"Î´×¢²á");
		}
		try {
			SeckillExecution seckillExecution = seckillService.executeSeckillProducer(seckillId, phone, md5);
			return new SeckillResult<SeckillExecution>(true,seckillExecution);
		} catch(RepeatKillException e) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId,SeckillEnum.REPEAT);
			seckillResult = new SeckillResult<SeckillExecution>(true,seckillExecution);
		} catch(SeckillCloseException e) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId,SeckillEnum.END);
			seckillResult = new SeckillResult<SeckillExecution>(true,seckillExecution);
		} catch(Exception e) {
			SeckillExecution seckillExecution = new SeckillExecution(seckillId,SeckillEnum.INNER_ERROR);
			seckillResult = new SeckillResult<SeckillExecution>(true,seckillExecution);
		} 
		return seckillResult;
	}
	@RequestMapping(value="/time/now",method=RequestMethod.GET,produces= {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Long> getTime() {
		Date now = new Date();
		SeckillResult<Long> seckillResult = new SeckillResult<Long>(true,now.getTime());
		return seckillResult;
	}
}
