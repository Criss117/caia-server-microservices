"use client";

import React from "react";
import Link from "next/link";

import { Button } from "@/core/shared/components/ui/button";
import { conferenceRoutes } from "@/core/shared/lib/constants/routes";
import { usePathname } from "next/navigation";
import { cn } from "@/core/shared/lib/utils";

const SideBar = () => {
  const pathname = usePathname();

  const isCurrentPath = (path: string) => pathname === path;

  return (
    <>
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
                <Link href={route.path}>
                  <route.Icon className="mr-2" />
                  <p>{route.name}</p>
                </Link>
              </Button>
            ))}
          </nav>
        </div>
      ))}
    </>
  );
};

export default SideBar;
