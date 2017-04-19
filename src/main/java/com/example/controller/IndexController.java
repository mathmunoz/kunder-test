package com.example.controller;

import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Word;

@RestController
public class IndexController {
	
	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("word",new Word()); // entrego el objeto para poder agregarle datos
		return "index";
	}
	
	@RequestMapping(value = "/word", method = RequestMethod.POST, produces = "application/json") //, headers="Accept=application/json"
	public ResponseEntity<Word> transformar(@RequestBody Word palabra){
		Word newWord = new Word();
		String word = palabra.getData();		// obtenemos la palabra ingresada
		String newData = word.toUpperCase(); 	// pasamos la palabra a mayuscula
		newWord.setData(newData); 
		
		//return "redirect:/word" + newWord;
		return new ResponseEntity<Word>(newWord, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/time", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<DateTimeFormatter> mostrarHora(@RequestBody int time) {
		/*
		final Date date = new Date();
		final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS zzz";
		final SimpleDateFormat sdf = new SimpleDateFormat(ISO_FORMAT);
		final TimeZone utc = TimeZone.getTimeZone("UTC");
		Date hora = (sdf.setTimeZone(utc)).format(date);
		*/
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS zzz");
		//String output = new DateTime( DateTimeZone.UTC );
		//String output = ZonedDateTime.now( ZoneOffset.UTC ).withNano( 0 ).toString();
		
		return new ResponseEntity<DateTimeFormatter>(dtf, HttpStatus.OK);
	}
	
	
	
	/* 
	 * PRUEBA FUNCIONAL: se genera un json y se muestra en la url /words
	 * 
	@RequestMapping(value = "/words", method = RequestMethod.GET,headers="Accept=application/json")
	public List<Word> getWords(){
		List<Word> listOfCountries = new ArrayList<Word>();
		listOfCountries = createCountryList();
		return listOfCountries;
	}
    
	public List<Word> createCountryList() {
		Word indiaCountry = new Word("India");
		Word chinaCountry = new Word("China");
		Word nepalCountry = new Word("Nepal");
		Word bhutanCountry = new Word("Bhutan");

		List<Word> listOfCountries = new ArrayList<Word>();
		listOfCountries.add(indiaCountry);
		listOfCountries.add(chinaCountry);
		listOfCountries.add(nepalCountry);
		listOfCountries.add(bhutanCountry);
		return listOfCountries;
	}
	*/
	
	/* 
	 * PRUEBA
	@RequestMapping(value = "/word", method=RequestMethod.POST)
	@ResponseBody
	public void seeJson(@RequestBody Word palabra, HttpServletRequest request, HttpServletResponse response) {
		Word newWord = new Word();
		String word = palabra.getData();
		String newData = word.toUpperCase();
		newWord.setData(newData);
		
		System.out.println(newWord);
	}
	*/
	
}
