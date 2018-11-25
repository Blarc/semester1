/* Includes ------------------------------------------------------------------*/
#include "stm32f4_discovery.h"

/* Private typedef -----------------------------------------------------------*/
/* Private define ------------------------------------------------------------*/
/* Private macro -------------------------------------------------------------*/
/* Private variables ---------------------------------------------------------*/
/* Private function prototypes -----------------------------------------------*/
/* Private functions ---------------------------------------------------------*/

int main(void)
{

	//https://stm32f4-discovery.net/2014/08/stm32f4-external-interrupts-tutorial/

	// init CLOCKS
    // LEDS
	RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOD, ENABLE);
    // BTN
	RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOA, ENABLE);
    // SYSCFG
    RCC_APB2PeriphClockCmd(RCC_APB2Periph_SYSCFG, ENABLE);

	// init SYSCFG
	SYSCFG_EXTILineConfig(EXTI_PortSourceGPIOA, EXTI_PinSource0);

	// init NVIC
	NVIC_InitTypeDef NVIC_InitStructure;
	NVIC_InitStructure.NVIC_IRQChannel = EXTI0_IRQn;
	NVIC_InitStructure.NVIC_IRQChannelPreemptionPriority = 0;
	NVIC_InitStructure.NVIC_IRQChannelSubPriority = 0;
	NVIC_InitStructure.NVIC_IRQChannelCmd = ENABLE;
	NVIC_Init(&NVIC_InitStructure);

	// init EXTI
	EXTI_InitTypeDef EXTI_InitStructure;
	EXTI_InitStructure.EXTI_Line = EXTI_Line0;
	EXTI_InitStructure.EXTI_Mode = EXTI_Mode_Interrupt;
	EXTI_InitStructure.EXTI_Trigger = EXTI_Trigger_Rising;
	EXTI_InitStructure.EXTI_LineCmd = ENABLE;
	EXTI_Init(&EXTI_InitStructure);

	// init LED
	GPIO_InitTypeDef led;
	led.GPIO_Pin = GPIO_Pin_12 | GPIO_Pin_13 | GPIO_Pin_14 | GPIO_Pin_15;
	led.GPIO_Mode = GPIO_Mode_OUT;
	led.GPIO_Speed = GPIO_Speed_2MHz;
	led.GPIO_OType = GPIO_OType_PP;
	led.GPIO_PuPd = GPIO_PuPd_NOPULL;

	GPIO_Init(GPIOD, &led);

	// init BTN
	GPIO_InitTypeDef btn;
	btn.GPIO_Pin = GPIO_Pin_0;
	btn.GPIO_Mode = GPIO_Mode_IN;
	btn.GPIO_PuPd = GPIO_PuPd_NOPULL;

	GPIO_Init(GPIOA, &btn);
	
	while (1)
	{
	}
        
        
        /*

        HANDLER

        volatile int counter = 0;

        void EXTI0_IRQHandler() {
          volatile int d = 0x300000;
          if (EXTI_GetITStatus(EXTI_Line0)) {
            counter++;
            counter %= 16;

            GPIO_ResetBits(GPIOD, 0xF << 12);
            GPIO_SetBits(GPIOD, counter << 12);
            
            while (d--);

            EXTI_ClearITPendingBit(EXTI_Line0);
          }

        */
}