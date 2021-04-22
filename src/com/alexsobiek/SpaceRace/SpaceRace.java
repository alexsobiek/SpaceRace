package com.alexsobiek.SpaceRace;

import com.alexsobiek.SpaceRace.event.EventBus;
import com.alexsobiek.SpaceRace.event.listener.KeyInput;
import com.alexsobiek.SpaceRace.event.listener.WindowResize;
import com.alexsobiek.SpaceRace.graphics.Timer;
import com.alexsobiek.SpaceRace.graphics.Window;

/**
 * SpaceRace:
 * Written for COMP 170 Loyola University Chicago
 *
 * @author Alexander Sobiek
 * @version 1.0
 */

public class SpaceRace {
    public static EventBus EVENT_BUS;
    public static Config config;

    /**
     * Main entry point for Space Race
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        System.out.println("Starting Space Race");
        EVENT_BUS = new EventBus();
        config = new Config();
        //new TestClass();


        GameManager.start();
        new Window();
        new KeyInput();
        new WindowResize();

    }

//    public static final class TestClass implements Listener {
//        private final List<Long> avgList = new ArrayList<>();
//
//        public TestClass() {
//            SpaceRace.EVENT_BUS.subscribe(this);
//            for (int i = 0; i < 1000001; i++) {
//                SpaceRace.EVENT_BUS.post(new TestEvent(System.currentTimeMillis()));
//                if (i + 1 == 1000001) {
//                    System.out.println(formatOutput(avgList));
//                    System.out.println("Average over " + i + " events: " + avgList.stream().mapToLong(a -> a).average().getAsDouble() + "ms");
//                    break;
//                }
//            }
//        }
//
//        @EventHandler
//        public void onTest(TestEvent event) {
//            avgList.add(System.currentTimeMillis() - event.getCreateTime());
//        }
//
//        private String formatOutput(List<Long> results) {
//            StringBuilder builder = new StringBuilder();
//            int overCount = 0;
//            for (long result : results) {
//                overCount++;
//                if (overCount == 40) {
//                    builder.append("\n").append(result).append(", ");
//                    overCount = 0;
//                } else {
//                    builder.append(result).append(", ");
//                }
//            }
//            return builder.toString();
//        }
//    }
}
