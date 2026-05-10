package com.practice.greedy;

class GasStation {

    /*Gas Stations
There's a circular route which contains gas stations. At each station, you can fill your car with a certain amount of gas, and moving from that station to the next one consumes some fuel.

Find the index of the gas station you would need to start at, in order to complete the circuit without running out of gas. Assume your car starts with an empty tank. If it's not possible to complete the circuit, return -1. If it's possible, assume only one solution exists.

Example:
Input: gas = [2, 5, 1, 3], cost = [3, 2, 1, 4]
Output: 1
Explanation:

Start at station 1: gain 5 gas (tank = 5), costs 2 gas to go to station 2 (tank = 3).
At station 2: gain 1 gas (tank = 4), costs 1 gas to go to station 3 (tank = 3).
At station 3: gain 3 gas (tank = 6), costs 4 gas to go to station 0 (tank = 2).
At station 0: gain 2 gas (tank = 4), costs 3 gas to go to station 1 (tank = 1).
We started and finished the circuit at station 1 without running out of gas.
*/

    static void main(String[] args) {
        int[] gas = {2, 5, 1, 3};
        int[] cost = {3, 2, 1, 4};
        System.out.println("Starting station: " + canCompleteCircuit(gas, cost));
    }

    private static int canCompleteCircuit(int[] gas, int[] cost) {

        int totalGas = 0;
        int totalCost = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        if (totalGas < totalCost) {return -1;}

        int start = 0;
        int tank = 0;

        for (int i = 0; i < gas.length; i++) {
            tank += gas[i] - cost[i];

            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }

        return start;
    }
}
