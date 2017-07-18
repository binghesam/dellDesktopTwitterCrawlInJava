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

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>This is a code example of Twitter4J Streaming API - filter method support.<br>
 * Usage: java twitter4j.examples.stream.PrintFilterStream [follow(comma separated numerical user ids)] [track(comma separated filter terms)]<br>
 * </p>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class testStream {
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
        
        //from the website to learn and test
        ConfigurationBuilder cb = new ConfigurationBuilder();  
        cb.setDebugEnabled(true)  
          .setOAuthConsumerKey("7ZVgfKiOvBDcDFpytRWSA")  
          .setOAuthConsumerSecret("JmeJVeym78arzmGthrDUshQyhkq6nWA9tWLUKxc")  
          .setOAuthAccessToken("321341780-Zy7LptVYBZBVvAeQ5GFJ4aKFw8sdqhWBnvA3pDuO")  
          .setOAuthAccessTokenSecret("foi8FnQCeN0J5cdwad05Q6d7dbytFayQn1ZOvmhF6Qc");  
        //cb.setJSONStoreEnabled(true); 
        
        TwitterStreamFactory tf = new TwitterStreamFactory(cb.build()); 
        TwitterStream twitterStream =tf.getInstance();
        //TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        
        //get the status
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
//            	/*
//            	 * orginal processing
//            	 */
//                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
//                System.out.println("*************one tweet*********\n");
//                
//                
                /*
                 * bing processing
                 */
                System.out.println(status);
                System.out.println(status.getGeoLocation());
                System.out.println("*************one tweet*********\n");
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
//        ArrayList<Long> follow = new ArrayList<Long>();
//        ArrayList<String> track = new ArrayList<String>();
//        
//        for (String arg : args) {
//            if (isNumericalArgument(arg)) {
//                for (String id : arg.split(",")) {
//                    follow.add(Long.parseLong(id));
//                }
//            } else {
//                track.addAll(Arrays.asList(arg.split(",")));
//            }
//        }
//        
//        
//        long[] followArray = new long[follow.size()];
//        for (int i = 0; i < follow.size(); i++) {
//            followArray[i] = follow.get(i);
//        }
//        String[] trackArray = track.toArray(new String[track.size()]);
//        // filter() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.   
//        twitterStream.filter(new FilterQuery(0, followArray, trackArray));
        
//        /*
//         * the website used files to get the filter for Bing to have a test
//         */
//        
//        String[] trackArray;  
//        String[] Track = {"IMDB", "movie","film","cinema", };  
//        //trackArray[0] = "Obama";  
//        //trackArray[1] = "Romney";  
//          
//        FilterQuery filter = new FilterQuery();  
//        filter.track(Track);  
//        twitterStream.filter(filter);  
//        //pr.mongo.close(); 
        
        /*
         * from stackOverFlow, the location filter.
         */
        
        FilterQuery filter = new FilterQuery();
        //String keyword[]= {"flu"};

        double [][]location ={{-122.53, 47.46},{-122.20,47.74}};

        //filter.track(keyword);

        filter.locations(location);
        

        twitterStream.filter(filter);
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
}