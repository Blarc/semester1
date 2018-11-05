/* Includes ------------------------------------------------------------------*/
#include "stm32f4_discovery.h"

/* Private typedef -----------------------------------------------------------*/
/* Private define ------------------------------------------------------------*/
#define DEVICE_ON ((uint32_t *) 0x40023830)
#define LED_MODE ((uint32_t *) 0x40020C00)
#define LED_TYPE ((uint32_t *) 0x40020C04)
#define LED_SPEED ((uint32_t *) 0x40020C08)
#define LED_PULL ((uint32_t *) 0x40020C0C)

#define LED_ON ((uint32_t *) 0x40020C18)
#define LED_OFF ((uint32_t *) 0x40020C1A)

#define BTN_MODE ((uint32_t *) 0x40020000)
#define BTN_PULL ((uint32_t *) 0x4002000C)
#define BTN ((uint32_t *) 0x40020010)

/* Private macro -------------------------------------------------------------*/
/* Private variables ---------------------------------------------------------*/
/* Private function prototypes -----------------------------------------------*/
/* Private functions ---------------------------------------------------------*/

/**
  * @brief   Main program
  * @param  None
  * @retval None
  */

int main(void)
{
    // prižge napravi A in D
    *DEVICE_ON = 0x09;
    
    //inicializacija LEDic
    *LED_MODE =  0x55555555;
    *LED_TYPE = 0;
    *LED_SPEED = 0;
    *LED_PULL = 0;
    
    //prižgi
    //*LED_ON = 0xF000;
    //ugasni
    //*LED_OFF = 0xF000;
    
    
  
  while (1)
  {
    if (*BTN & (1 << 0)){
      for (int i = 0; i < 4; i++) {
        *LED_ON = 0xF000;
        volatile uint32_t d = 0x1000000;
        while(d--);
        d = 0x1000000;
        *LED_OFF = 0xF000;
        while(d--);
      }
    }
  }
}