import { PropsWithChildren } from "react";
import SideBar from "./sidebar";
import SheetSideBar from "./sheet-sidebar";

interface Props extends PropsWithChildren {
  className?: string;
}

const DashBoardSideBar = ({ children }: Props) => {
  return (
    <div className="flex h-full pt-14">
      <aside className="hidden bg-lightbg-300/50 lg:w-1/4 xl:w-1/5 border-r border-black px-5 pt-10 fixed h-full lg:flex flex-col">
        <SideBar />
      </aside>
      <aside className="lg:hidden border-black fixed h-full flex flex-col">
        <div className="h-full border absolute border-black -z-10 right-[50%]"></div>
        <SheetSideBar />
      </aside>
      <div className="hidden lg:block lg:w-1/4 xl:w-1/5"></div>
      <div className="w-full lg:w-3/4 xl:w-4/5 pt-10 px-14">{children}</div>
    </div>
  );
};

export default DashBoardSideBar;
