/* Includes ------------------------------------------------------------------*/
#include "stm32f4_discovery.h"

/* Private define ------------------------------------------------------------*/


/* Private structs -----------------------------------------------------------*/
 

/* Private function prototypes -----------------------------------------------*/


/* Private functions ---------------------------------------------------------*/


/**
  * @brief   Main program
  * @param  None
  * @retval None
  */
int main(void)
{
  RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOD, ENABLE);
  RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOA, ENABLE);
  
  GPIO_InitTypeDef a;
  GPIO_InitTypeDef b;
  
  a.GPIO_Pin = GPIO_Pin_12|GPIO_Pin_13|GPIO_Pin_14|GPIO_Pin_15;
  a.GPIO_Mode = GPIO_Mode_OUT;
  a.GPIO_OType = GPIO_OType_PP;
  a.GPIO_Speed = GPIO_Speed_2MHz;
  a.GPIO_PuPd = GPIO_PuPd_NOPULL;
  
  b.GPIO_Pin = GPIO_Pin_0;
  b.GPIO_Mode = GPIO_Mode_IN;
  b.GPIO_OType = GPIO_OType_PP;
  b.GPIO_Speed = GPIO_Speed_2MHz;
  b.GPIO_PuPd = GPIO_PuPd_NOPULL;
  
  GPIO_Init(GPIOD, &a);
  GPIO_Init(GPIOA, &b);

  while (1)
  {
    uint8_t btn = GPIO_ReadInputDataBit(GPIOA, GPIO_Pin_0);
      //if (btn & (1 << 0)) {
        for (int i = 0; i < 4; i++) {
          if (i == 1) {
            GPIO_SetBits(GPIOD, GPIO_Pin_12);
          }
          else if (i == 2) {
            GPIO_SetBits(GPIOD, GPIO_Pin_13);
          }
          else if (i == 3) {
            GPIO_SetBits(GPIOD, GPIO_Pin_14);
          }
          else {
            GPIO_SetBits(GPIOD, GPIO_Pin_15);
          }
          
          volatile uint32_t d = 0x0080000;
          while(d--);
          d = 0x0080000;
          GPIO_ResetBits(GPIOD, GPIO_Pin_12|GPIO_Pin_13|GPIO_Pin_14|GPIO_Pin_15);
          while(d--);
        }
    //}
  }
}
