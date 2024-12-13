"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { ChevronRight } from "lucide-react";

import { Button } from "@/core/shared/components/ui/button";
import CaiaTitle from "@/core/shared/components/ui/caia-title";
import {
  Sheet,
  SheetContent,
  SheetDescription,
  SheetHeader,
  SheetTitle,
  SheetTrigger,
} from "@/core/shared/components/ui/sheet";
import { conferenceRoutes } from "@/core/shared/lib/constants/routes";
import { cn } from "@/core/shared/lib/utils";
import { useState } from "react";

const SheetSideBar = () => {
  const [open, setOpen] = useState(false);
  const pathname = usePathname();

  const isCurrentPath = (path: string) => pathname === path;

  return (
    <Sheet open={open} onOpenChange={setOpen}>
      <SheetTrigger asChild>
        <Button variant="outline" size="icon" className="rounded-full mt-10">
          <ChevronRight className="h-4 w-4" />
        </Button>
      </SheetTrigger>
      <SheetContent className="w-[300px] sm:w-[540px]" side="left">
        <SheetHeader>
          <SheetTitle>
            <CaiaTitle toDashboard={false} />
          </SheetTitle>
          <SheetDescription></SheetDescription>
        </SheetHeader>
        {conferenceRoutes.map((route, index) => (
          <div className="w-full mt-10" key={index}>
            <h3 className="font-bold text-xl">{route.name}</h3>
            <nav className="mt-5 space-y-2">
              {route.routes.map((route, index) => (
                <Button
                  asChild
                  variant="ghost"
                  key={index}
                  className={cn(
                    "w-full justify-start",
                    isCurrentPath(route.path) && "font-bold bg-accent"
                  )}
                >
                  <Link href={route.path} onClick={() => setOpen(false)}>
                    <route.Icon className="mr-2" />
                    <p>{route.name}</p>
                  </Link>
                </Button>
              ))}
            </nav>
          </div>
        ))}
      </SheetContent>
    </Sheet>
  );
};

export default SheetSideBar;
