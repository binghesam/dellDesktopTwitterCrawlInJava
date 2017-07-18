/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package twitter4JBing;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder; 
//import twitter4j.StallWarning; this is wrong becasue this is in the core not in the stream.obj

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintStream;
import java.util.*;
import java.io.*;

/**
 * <p>This is a code example of Twitter4J Streaming API - filter method support.<br>
 * Usage: java twitter4j.examples.stream.PrintFilterStream [follow(comma separated numerical user ids)] [track(comma separated filter terms)]<br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class MultiThreadLocationAdvancedFillStreamFilterLinux00 {
    /**
     * Main entry of this application.
     *
     * @param args follow(comma separated user ids) track(comma separated filter terms)
     * @throws TwitterException when Twitter service or network is unavailable
     */
    public static void main(String[] args) throws TwitterException {
    	
//    	/*
//    	 * original part in the twitter4j.java for the judgement.
//    	 */
//        if (args.length < 1) {
//            System.out.println("Usage: java twitter4j.examples.PrintFilterStream [follow(comma separated numerical user ids)] [track(comma separated filter terms)]");
//            System.exit(-1);
//        }
//        
        try{
            
        	int locatChose=0; //argv[1] //by bing, later to choose it. index from 0,1,2
        	String directPrefix="./";
        	
    		/*
    		 * by bing the location configuration
    		 */
        	double [][]la ={{-118.65 ,33.71},{-118.16, 34.19}};
        	double [][]ny ={{-74.03, 40.50},{-73.70, 40.91}};
        	double [][]ch ={{-87.93,41.64},{-87.52,42.02}};
        	
        	ArrayList<double[][]> locationList=new ArrayList<double[][]>();
        	locationList.add(la);
        	locationList.add(ny);
        	locationList.add(ch);
        	String[] locationListName={"la","ny","ch"};
        	double[][] location=locationList.get(locatChose);
        	//index from 0,1,2
        	
        	
        	
    		
        	/*
        	 * configure the cosumerKey related
        	 */
        	
        	//config[] in the order on consumKey,secret,token,tokenSecret
        	String[] config1={"GZk0ib347emk26iNF3jbilAPB",
        			"o3qQ3HF4GcK7ncKW1ICszh7UYLwjmDtUhI1z01bcjXg3ZEBdbA",
        			"3249467239-1AvJcAxOvJ6ayTK9yyDv6I747mKFQjw8ODVLcjb",
        			"KhGEvqIQYiDvPEqyCTBcHVojX8AqiJtBCw541WbB2RobZ"};//in python, this is config0
        	String[] config2={"MjHnsmzPgRvd0YlpvQmxr9Gte",
        			"Zl3qBfY4V7AYPUDj16P7dys22MlJD5ClrJLx2jcMDd1beTwpxb",
        			"3249467239-ZqWYfLL7NWtday0gJvXxhwRu6zcLTFYdOAUsdt3",
        			"IaaCXwPcr2SDFEf6bJmtGQ8cy6aKFFfurem3HNGfme2KO"};//in python this is config1
        	String[] config3={"IAqI4OuwdNQPg67KeNQ2yhIXH",
        			"fjylbb915RC33tOQO9raJld4XN5QA5SAJWmO9I6KOHrrYUXqpi",
        			"3249467239-uPSAjim0boT3h4F3MTeIcZmLYf19MvwGZszMGeJ",
        			"Zeyy6Wym4hVmbzCdl83QsMlYuOYZPDShH50XKzBcQmp3F"};// in python this is config2
        	String[] config4={"36mv6gjj1pMDTDGnYIUykSZIm",
        			"tyFFUVEjkhCd4BWvZ8jAazifOb6Vpjy6q1wtiSvTEVaJAOcTt8",
        			"3249467239-h47c4eibh3xYooBjrfKdYWO1S47V9myQiwmJWey",
        			"an4sMklvI7lgUIATeH4p3AnYGOzMjJ538pYCp0e2v0NLu"};// in python this is config4
        	String[] config5={"TdYh7Pnfnrw4Ig6xVVeWd3Adt",
        			"QRd0vEZTD1f7c9PkxCeD5KbhLLOLPqFYeG2hWvaV1iUWwVY8Vu",
        			"876787781447467009-OH1T8AMq62VHjhuJAL0HggsMvrjzLLS",
        			"NqjQlA8UpslWdEdBwNdm89N6i0HLyqQ0aShLiXO7Qabzz"};// in python this is config5
        	
        	
        	ArrayList<String[]> configList=new ArrayList<String[]>();
        	configList.add(config1);
        	configList.add(config4);
        	configList.add(config5);//the 1,4,5 is not used in python linux running part.
        	
        	String[] config=configList.get(locatChose);
            ConfigurationBuilder cb = new ConfigurationBuilder();  
            cb.setDebugEnabled(true)
              .setOAuthConsumerKey(config[0])  
              .setOAuthConsumerSecret(config[1])  
              .setOAuthAccessToken(config[2])  
              .setOAuthAccessTokenSecret(config[3]);  
          //cb.setJSONStoreEnabled(true); 
            
//            //from the website to learn and test
//            ConfigurationBuilder cb = new ConfigurationBuilder();  
//            cb.setDebugEnabled(true)  
//              .setOAuthConsumerKey("7ZVgfKiOvBDcDFpytRWSA")  
//              .setOAuthConsumerSecret("JmeJVeym78arzmGthrDUshQyhkq6nWA9tWLUKxc")  
//              .setOAuthAccessToken("321341780-Zy7LptVYBZBVvAeQ5GFJ4aKFw8sdqhWBnvA3pDuO")  
//              .setOAuthAccessTokenSecret("foi8FnQCeN0J5cdwad05Q6d7dbytFayQn1ZOvmhF6Qc");  
//            //cb.setJSONStoreEnabled(true); 
            
            TwitterStreamFactory tf = new TwitterStreamFactory(cb.build()); 
            TwitterStream twitterStream =tf.getInstance();
            //TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
            
            //get the status
            StatusListener listener = new StatusListener() {           	
            	
            	/*
            	 * by bing the time configuration
            	 */
            	//by bing setting the time for the processing
        		ZonedDateTime current=ZonedDateTime.now();
        		
        		int  year=current.getYear();
        		String month=String.format("%02d", current.getMonthValue());
        		String day= String.format("%02d", current.getDayOfMonth());
        		String hour=String.format("%02d", current.getHour());
        		String minut=String.format("%02d", current.getMinute());
        		String currTime="day"+String.valueOf(month)+String.valueOf(day)+"time"+String.valueOf(hour)+String.valueOf(minut);
        		
        		int hourCompare=current.getHour();
        		int minutCompare=current.getMinute();
        		
        		
        		//String currTime=String.valueOf(year)+"day"+String.valueOf(month)+String.valueOf(day)+"time"+String.valueOf(hour)+String.valueOf(minut);

        		String outputFile=directPrefix+locationListName[locatChose]+currTime+".txt";
        		String outputFileError=directPrefix+locationListName[locatChose]+currTime+"Err"+".txt";
        		//PrintStream pst=new PrintStream(outputFileError);//by bing ,if updated, we should close it.
        		
        		//System.out.println(year+month+day+hour+minut);
        		//time processing for getting
        		
            	
                @Override
                public void onStatus(Status status) {
//                	/*
//                	 * orginal processing
//                	 */
//                    System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
//                    System.out.println("*************one tweet*********\n");
//                    
//                    
                    /*
                     * bing processing
                     */
                    //System.out.println(status);
                    //System.out.println(status.getGeoLocation());
                    //System.out.println("*************one tweet*********\n");
                	
                    //String pathDirectory="./test.txt";
                	
//                    try{
//                    	writeStringToFile.writeStringToFileProcess(outputFile,status.toString());
//                    }catch(Exception e){
//                    	e.printStackTrace(); 
//                    }
                	//if((ZonedDateTime.now().getMinute()-minutCompare)<=2){ //by bing, used for debugging
                	if(ZonedDateTime.now().getHour()==hourCompare){	//by bing, used in the real system
                		writeStringToFileProcess(outputFile,status.toString());
                	}else{
                		
                		current=ZonedDateTime.now();     
                		
                		year=current.getYear();                		
                		month=String.format("%02d", current.getMonthValue());
                		day= String.format("%02d", current.getDayOfMonth());
                		hour=String.format("%02d", current.getHour());
                		minut=String.format("%02d", current.getMinute());
                		
                		currTime="day"+String.valueOf(month)+String.valueOf(day)+"time"+String.valueOf(hour)+String.valueOf(minut);
                	
                		hourCompare=current.getHour();
                		minutCompare=current.getMinute();
                		
                		outputFile=directPrefix+locationListName[locatChose]+currTime+".txt";
                		outputFileError=directPrefix+locationListName[locatChose]+currTime+"Err"+".txt";
                		
                		writeStringToFileProcess(outputFile,status.toString());
                	}
                	
                    
					//writeStringToFile.writeStringToFileProcess(outputFile,status.toString());
                    
                }

                @Override
                public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                    System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
                }

                @Override
                public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                    System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
                }

                @Override
                public void onScrubGeo(long userId, long upToStatusId) {
                    System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
                }

                @Override
                public void onStallWarning(StallWarning warning) {
                    System.out.println("Got stall warning:" + warning);
                }

                @Override
                public void onException(Exception ex) {
                    ex.printStackTrace();
                    System.out.println("************there is an error in the streaming part***********");
                }
            };
            
            twitterStream.addListener(listener);
            /*
             * original stream filter part in the twitter4j.java files
             */
//            ArrayList<Long> follow = new ArrayList<Long>();
//            ArrayList<String> track = new ArrayList<String>();
//            
//            for (String arg : args) {
//                if (isNumericalArgument(arg)) {
//                    for (String id : arg.split(",")) {
//                        follow.add(Long.parseLong(id));
//                    }
//                } else {
//                    track.addAll(Arrays.asList(arg.split(",")));
//                }
//            }
//            
//            
//            long[] followArray = new long[follow.size()];
//            for (int i = 0; i < follow.size(); i++) {
//                followArray[i] = follow.get(i);
//            }
//            String[] trackArray = track.toArray(new String[track.size()]);
//            // filter() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.   
//            twitterStream.filter(new FilterQuery(0, followArray, trackArray));
            
//            /*
//             * the website used files to get the filter for Bing to have a test
//             */
//            
//            String[] trackArray;  
//            String[] Track = {"IMDB", "movie","film","cinema", };  
//            //trackArray[0] = "Obama";  
//            //trackArray[1] = "Romney";  
//              
//            FilterQuery filter = new FilterQuery();  
//            filter.track(Track);  
//            twitterStream.filter(filter);  
//            //pr.mongo.close(); 
            
            /*
             * from stackOverFlow, the location filter.
             */
            
            FilterQuery filter = new FilterQuery();
            //String keyword[]= {"flu"};

           // double [][]location ={{-122.53, 47.46},{-122.20,47.74}};

            //filter.track(keyword);

            filter.locations(location);           
            twitterStream.filter(filter);  
} catch(Exception e){
	e.printStackTrace(); 
	writeStringToFileProcess("./ErrorCollect.txt",e.getMessage());
	//writeStringToFile.writeStringToFileProcess("./ErrorCollect.txt",e.getMessage());	
	//System.setErr(".txt"); if possible later check it
}
}


	 public static void writeStringToFileProcess(String filePathAndName, String stringToBeWritten){    
	        try
	        {
	            String filename= filePathAndName;
	            boolean append = true;
	            FileWriter fw = new FileWriter(filename,append);
	            fw.write(stringToBeWritten);//appends the string to the file
	            fw.write("\n");
	            fw.close();
	        }
	        catch(IOException ioe)
	        {
	            System.err.println("IOException: " + ioe.getMessage());
	            System.out.println("Stop in write files");
	            //System.exit(-1);
	        }
	    }
	 }


//    /*
//     * orinial code for the arguments processing
//     */
//    private static boolean isNumericalArgument(String argument) {
//        String args[] = argument.split(",");
//        boolean isNumericalArgument = true;
//        for (String arg : args) {
//            try {
//                Integer.parseInt(arg);
//            } catch (NumberFormatException nfe) {
//                isNumericalArgument = false;
//                break;
//            }
//        }
//        return isNumericalArgument;
//    }
