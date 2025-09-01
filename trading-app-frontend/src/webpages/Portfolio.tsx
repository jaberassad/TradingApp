import React, { FC, useEffect, useRef, useState } from "react";
import SummedTransaction from "../interfaces/SummedTransaction";
import { Doughnut } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import Transaction from "../interfaces/Transaction";

ChartJS.register(ArcElement, Tooltip, Legend);

interface PortfolioProps {
  summedElements: SummedTransaction[];
  balance: number;
}

interface HoveredChartElement {
  element: ArcElement;
  datasetIndex: number;
  index: number;
}

const Portfolio: FC<PortfolioProps> = (props) => {
  const [newChartElementIndex, setNewChartElementIndex] = useState<number>(-1);
  const [oldChartElementIndex, setOldChartElementIndex] = useState<number>(-1);
  const [loadingProps, setLoadingProps] = useState<boolean>(true);
  const [backgroundColors, setBackgroundColors] = useState<string[]>([]);
  const [ownedAssets, setOwnedAssets] = useState<SummedTransaction[]>([]);
  const [stockValues, setStockValues] = useState<number[]>([]);
  const [stockLabels, setStockLabels] = useState<String[]>([]);
  const listItemRefs = useRef<Map<number, HTMLLIElement>>(new Map());

  const getCurrentPrice = async (assetName: String): Promise<number> => {
    setLoadingProps(true);
    let response = await fetch(`${process.env.REACT_APP_API_URL}api/assets/${assetName}`);
    const data = await response.json()

    setLoadingProps(false); 

    if (data.prices && data.prices !== "[]") {
        data.prices = JSON.parse(data.prices)
        return data.prices[0];
    }else{
      return 100 * Math.random(); 
    }
  };
  
  const getRandomColor = (): string => {
    const letters = "0123456789ABCDEF";
    let color = "#";
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  };

  useEffect(() => {
    if (!props.summedElements || props.summedElements.length === 0) return;

    const filtered = props.summedElements.filter(i => i.numOfAssets > 0);
    setOwnedAssets(filtered);  // still update state if needed for rendering
    prepareData(filtered);      // pass the filtered array directly
  }, [props.summedElements]);


  if(ownedAssets.length==0) return <></>


  const onHover = (e: any, item: any) => {
    setOldChartElementIndex(newChartElementIndex);
    if (Array.isArray(item) && item.length > 0) {
      const hoveredChartElement: HoveredChartElement = item[0];

      setNewChartElementIndex(hoveredChartElement.index);

      if (newChartElementIndex === oldChartElementIndex) {
        return;
      }

      higlightPortfolioElement(newChartElementIndex, false);
      higlightPortfolioElement(oldChartElementIndex, true);
    } else {
      higlightPortfolioElement(oldChartElementIndex, true);
      setNewChartElementIndex(-1);
    }
  };

  const higlightPortfolioElement = (key: number, leave: boolean) => {
    const listItem = listItemRefs.current.get(key);
    const classes = "portfolio-li-hover";
    if (leave) {
      listItem?.classList.remove(classes);
    } else {
      listItem?.classList.add(classes);
    }
  };

  
  // Calculate the value of each stock
  async function prepareData(elements: typeof ownedAssets) {
    setLoadingProps(true);

    const values = await Promise.all(
      elements.map(async ({ numOfAssets, assetName }) => {
        const currentPrice = await getCurrentPrice(assetName);
        backgroundColors.push(getRandomColor())
        return numOfAssets * currentPrice;
      })
    );

    setStockValues(values);
    setStockLabels(
      elements.map(({ numOfAssets, assetName }) =>
        `${assetName} (${numOfAssets} stock${numOfAssets !== 1 ? "s" : ""})`
      )
    );

    setLoadingProps(false);
  }


  // Prepare data for the Donut chart
  const data = {
    labels: stockLabels,
    datasets: [
      {
        data: stockValues,
        backgroundColor: backgroundColors,
        hoverBackgroundColor: backgroundColors,
        borderWidth: 1,
      },
    ],
  };

  const options = {
    responsive: true,
    maintainAspectRatio: false,
    aspectRatio: 1.5,
    plugins: {
      legend: {
        position: "bottom" as const, // Position of the legend
        labels: {
          font: {
            size: 12, // Font size of the labels
          },
          boxWidth: 10, // Width of the legend box
          padding: 20, // Padding between legend items and the chart
        },
      },
    },
    onHover: (e: any, item: any) => onHover(e, item),
  };

  if (loadingProps) {
    return <div>loading</div>;
  }


  return (
    <div className="portfolio">
      <h2 className="mb-4 text-xl">Portfolio</h2>
      <div className="flex flex-row">
        {stockValues.length>0 && <ul> 
          {ownedAssets.map((summedTransaction, index) => {
            if (summedTransaction.numOfAssets === 0) {
              return;
            }
            
            console.log(stockValues)
            const currPrice = stockValues[index];

            return (
              <li
                key={index}
                ref={(el) => {
                  if (el) {
                    listItemRefs.current.set(index, el);
                  } else {
                    listItemRefs.current.delete(index);
                  }
                }}
              >
                <b> 
                  {summedTransaction.numOfAssets} stock
                  {summedTransaction.numOfAssets != 1 ? "s" : ""}
                </b>{" "}
                of{" "}
                <span className=" text-purple-900">
                  {summedTransaction.assetName}
                </span>{" "}
                at{" "}
                <span className="text-stone-600">
                  ${(currPrice / summedTransaction.numOfAssets).toFixed(2)}
                </span>{" "}
                a share, for a total value of{" "}
                <span className=" text-green-900">
                  $
                  {(currPrice)}
                </span>
                .
              </li>
            );
          })}
        </ul>}

        <div className="w-1/2 h-96">
          <Doughnut data={data} options={options} />
        </div>
      </div>
    </div>
  );
};

export default Portfolio;
