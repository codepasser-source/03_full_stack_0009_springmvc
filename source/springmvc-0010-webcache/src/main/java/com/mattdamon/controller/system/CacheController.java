package com.mattdamon.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mattdamon.model.system.FormatModel;

@Controller
@RequestMapping(value = "/cache")
public class CacheController {

	@RequestMapping(value = "/view.action", method = RequestMethod.GET)
	public ModelAndView view(HttpServletRequest request, Model model) {
		if (!model.containsAttribute("contentModel")) {

			FormatModel formatModel = new FormatModel();

			formatModel.setMoney(12345.678);
			formatModel.setDate(new Date());

			model.addAttribute("contentModel", formatModel);
		}

		return new ModelAndView("/view/cache/view.html");
	}


}
