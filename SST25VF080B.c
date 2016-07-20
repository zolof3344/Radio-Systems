/*
 * SST25VF080B.c
 *
 *  Created on: April 28th, 2016
 *  Author: Michael Holloway and Daniel Johnson
 *  Version 1.3
 *  This file provides functions for writing and reading
 *  To the SST25VF080B memory chip the chip comes in two variants
 *  16 Mb and 8Mb
 *  SST25VF080B PINOUT To Flight Computer MSP430
 *  =====================================
 *  1. !CE P1.0 -> IO32 -> H2#16
 *  2.  SO(RX) P3.5 -> IO4 -> H119
 *  3. !WP Tied High
 *  4.  GND
 *  5.  SI(TX) P3.4 -> IO5 -> H120
 *  6.  SLK    P3.0 -> IO0 -> H118
 *  7. !HLD Tied high
 *  8. VCC 3.3V
 */
/* Confirmed Working
 * [x] isBusy()
 * [x] chipErase()
 * [x] spiRead()
 * [x] spiWrite()
 * [x] writeEnable()
 * [x] writeDisable()
 * [x] readStatusRegister()
 * [x] unprotect()
 * [x] singleByteWrite()
 * [x] singleByteRead()
 * [x] setCE()
 * Unconfirmed
 * [ ] chipErase()
 * [ ] aiiWrite()
 * [ ] fullChipRead()
 */
#include <msp430F2618.h>
#include <string.h>
#include "SST25VF080B.h"
int isBusy()
{
		return readStatusRegister() & BIT0;

}
void chipErase()
{
	setCE(0); //set the chip Enable low
	spiWrite(0x60); // send the chip erase command
	while(isBusy()); //while the busy flag is 1, wait
    setCE(BIT0);
}
/*
 * Write using Auto Address Increment
 */

long aiiWrite(long address, char* data,  long amount)
{
	long count = 0; //a count for the amoutn of data being written
	setCE(0); //drive chip enable low
	writeEnable(); // write enable
	setCE(1);
	setCE(0);
	spiWrite(0xAD); // send Ad for auto address increment
	spiWrite((address >> 16) & 0xFF); // send the address parts
	spiWrite((address >> 8) & 0xFF); // send address parts
	spiWrite(address & 0xFF); // send address parts
	while(isBusy());
	while(count <= amount) // as long as we are not finished go
	{

		setCE(0); // set chip enable low
     if(count>0)
     {
    	 spiWrite(0xAD);
     }
		spiWrite(data[count++]); //send our data
		spiWrite(data[count++]); // send more data

		setCE(BIT0); //set chip enable high
		address+=2; //increment the address by 2 to keep track of the address
		while(isBusy());

	}

	writeDisable(); // write disable to end the cycle
	setCE(1);        //set chip enable low
	readStatusRegister();
	return address;

}
int spiRead()
{
    // Reads one byte from the spi bus
	int RXDATA;
	spiWrite(0x00);
	while(UCA0STAT & UCBUSY);
	RXDATA = UCA0RXBUF;
    return RXDATA;
}
void spiWrite(char data)
{
    //write the data to the TX buffer
    while(UCA0STAT & UCBUSY);
    UCA0TXBUF = data;

}

char fullChipRead()
{
  char data = 0;
  long address = 0;
  while(address<0x20000)
  {
	  data = singleByteRead((address >> 16) & 0xFF,(address >> 8) & 0xFF,address & 0xFF);
	  /*
	   * Do something with the data here, for instance, a Uart transfer
	   */
      address+=2;
  }
  return data;
}
void writeEnable()
{
    // Write CE LOW
	P1OUT &= ~BIT0;
    spiWrite(0x06);
}
void writeDisable()
{
    // Write CE LOW
	P1OUT &= ~BIT0;
    spiWrite(0x04);
}
int readStatusRegister()
{
    //Reads the status register, basically a test of read and write
    // Write CE LOW
    P1OUT &= ~BIT0;
	spiWrite(0x05);
	int statusRegister;
	statusRegister = spiRead();
	return statusRegister;
}
void unprotect()
 {
    P1OUT &= ~BIT0;
	 spiWrite(0x01);  // write status register
	 spiWrite(0x00);  // zeroes
 }
void singleByteWrite(char data, char add1, char add2, char add3)
{
    spiWrite(0x02); // single byte program
    spiWrite(add1);
    spiWrite(add2);
    spiWrite(add3);
    spiWrite(data);
}
char singleByteRead(char add1, char add2, char add3)
{   setCE(0);
    spiWrite(0x03);
    spiWrite(add1);
    spiWrite(add2);
    spiWrite(add3);
    char data;
    data = spiRead();
    setCE(BIT0);
    return data;
}
/*
 * SetCE takes in a flag if the flag is 1 Chip enable
 * set high if the flag is anythign else the chip enable is set low
 */
void setCE(int flag)
{  if(flag&BIT0)
   {
    P1OUT |= BIT0;
   }
   else
   {
	P1OUT &= ~BIT0;
   }
}
