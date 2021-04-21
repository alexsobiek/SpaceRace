package com.alexsobiek.SpaceRace;

import com.alexsobiek.SpaceRace.event.EventBus;
import com.alexsobiek.SpaceRace.event.EventHandler;
import com.alexsobiek.SpaceRace.event.Listener;
import com.alexsobiek.SpaceRace.event.events.TestEvent;
import com.alexsobiek.SpaceRace.event.listener.KeyInput;

import java.util.ArrayList;
import java.util.List;

/**
 * SpaceRace:
 * Written for COMP 170 Loyola University Chicago
 *
 * @author Alexander Sobiek
 * @version 1.0
 */

public class SpaceRace {
    public static EventBus EVENT_BUS;

    /**
     * Main entry point for Space Race
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        EVENT_BUS = new EventBus();
        //new TestClass();
        new KeyInput();
        new Timer();
        System.out.println("Starting Space Race");
        GameManager.start();
        new Window();
    }

//    public static final class TestClass implements Listener {
//        private final List<Long> avgList = new ArrayList<>();
//
//        public TestClass() {
//            SpaceRace.EVENT_BUS.subscribe(this);
//            for (int i = 0; i < 1000001; i++) {
//                SpaceRace.EVENT_BUS.post(new TestEvent(System.currentTimeMillis()));
//                if (i + 1 == 1000001) {
//                    System.out.println("Average over " + i + " events: " + avgList.stream().mapToLong(a -> a).average().getAsDouble() + "ms");
//                }
//            }
//        }
//
//        @EventHandler
//        public void onTest(TestEvent event) {
//            avgList.add(System.currentTimeMillis() - event.getCreateTime());
//        }
//        private void formatOutput(List<Long> results) {
//            StringBuilder builder = new StringBuilder();
//            int overCount = 0;
//            for (long result : results) {
//                overCount++;
//                if (overCount == 5) {
//                    builder.append("\n");//Not done but im making a commit rn
//                }
//            }
//        }
//    }
}
