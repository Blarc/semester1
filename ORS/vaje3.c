/* Includes ------------------------------------------------------------------*/
#include "stm32f4_discovery.h"

/* Private define ------------------------------------------------------------*/
#define	GPIOAd ((GPIO_device*) 0x40020000)
#define	GPIOBd ((GPIO_device*) 0x40020400)
#define	GPIOCd ((GPIO_device*) 0x40020800)
#define	GPIODd ((GPIO_device*) 0x40020C00)
#define	GPIOEd ((GPIO_device*) 0x40021000)
#define	GPIOFd ((GPIO_device*) 0x40021400)
#define	GPIOGd ((GPIO_device*) 0x40021800)
#define	GPIOHd ((GPIO_device*) 0x40021C00)
#define	GPIOId ((GPIO_device*) 0x40022000)

#define	IN 1
#define	OUT 0

#define	NO_PULL 0
#define	PULL_UP 1
#define	PULL_DOWN 2

#define	PUSH_PULL 0
#define	OPEN_DRAIN 1

#define	S2MHz 0
#define	S25MHz 1
#define	S50MHz 2
#define	S100MHz 3

/* Private structs -----------------------------------------------------------*/
typedef struct
{
  uint32_t MODER;
  uint32_t OTYPER;
  uint32_t OSPEEDR;
  uint32_t PUPDR;
  uint32_t IDR;
  uint32_t ODR;
  uint16_t SET;
  uint16_t RESET;
} GPIO_device; 

/* Private function prototypes -----------------------------------------------*/
void init_GPIO(GPIO_device * GPIO_addr, uint32_t Pin, uint32_t Mode, uint32_t PUPD, uint32_t OType, uint32_t OSpeed);
void ClockOn(GPIO_device * GPIO_addr);
void GPIO_Output(GPIO_device * GPIO_addr, uint32_t Pin, uint32_t val);
uint32_t GPIO_Read(GPIO_device * GPIO_addr, uint32_t Pin);

/* Private functions ---------------------------------------------------------*/
void init_GPIO(GPIO_device* GPIO_addr, uint32_t Pin, uint32_t Mode, uint32_t PUPD, uint32_t OType, uint32_t OSpeed)
{
  //manjka, set two bits
  GPIO_adrr -> MODER |= (Mode << Pin*2);
  GPIO_adrr -> PUPDR |= PUPD;
  GPIO_adrr -> OTYPER |= OType;
  GPIO_adrr -> OSPEEDR |= OSpeed;
  GPIO_adrr -> ODR += Pin;
}

void ClockOn(GPIO_device* GPIO_addr)
{
  GPIO_adrr -> MODER + 0x030 |= 1;
}

void GPIO_Output(GPIO_device* GPIO_addr, uint32_t Pin, uint32_t val) 
{
  GPIO_adrr -> ODR + Pin |= val;
  if (val == 1) {
    GPIO_addr -> SET = 1 << Pin;
  } else {
    GPIO_adrr -> RESET = 1 << Pin;
  }
}

uint32_t GPIO_Read(GPIO_device* GPIO_addr, uint32_t Pin)
{
  if (GPIO_addr -> IDR & (1 << Pin) ) {
      return 1;
  else {
      return 0;
  }
}


/**
  * @brief   Main program
  * @param  None
  * @retval None
  */
int main(void)
{
  init_GPIO(GPIOA, 
  while (1)
  {
  }
}
