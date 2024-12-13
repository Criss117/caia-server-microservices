"use client";

import { Menu, Search } from "lucide-react";
import React, { useState } from "react";
import {
  Sheet,
  SheetContent,
  SheetDescription,
  SheetHeader,
  SheetTitle,
  SheetTrigger,
} from "../ui/sheet";
import { Button } from "../ui/button";
import LinkAnimated from "../ui/link-animated";
import { ROUTES } from "../../lib/constants/routes";
import CaiaTitle from "../ui/caia-title";
import { Input } from "../ui/input";

const routes = [
  {
    name: "Crea conferencias",
    href: ROUTES.DASHBOARD.ROOT,
  },
  {
    name: "Registrarse",
    href: ROUTES.SIGNUP,
  },
  {
    name: "Iniciar sesión",
    href: ROUTES.LOGIN,
  },
];
const BurgerMenu = () => {
  const [open, setOpen] = useState(false);

  return (
    <Sheet open={open} onOpenChange={setOpen}>
      <SheetTrigger asChild>
        <Button variant="outline">
          <Menu className="h-4 w-4" />
        </Button>
      </SheetTrigger>
      <SheetContent className="w-[400px] sm:w-[540px]">
        <SheetHeader>
          <SheetTitle>
            <CaiaTitle toDashboard={false} />
          </SheetTitle>
          <SheetDescription></SheetDescription>
        </SheetHeader>
        <fieldset className="flex relative w-full">
          <Search className="absolute top-1/2 -translate-y-1/2 left-2" />
          <Input
            type="text"
            placeholder="Buscar Conferencias"
            className="pl-10"
          />
        </fieldset>
        <ul className="flex flex-col justify-center items-center h-full gap-y-10">
          {routes.map((route, index) => (
            <LinkAnimated
              key={index}
              href={route.href}
              className="text-2xl font-semibold"
              onClick={() => setOpen(false)}
            >
              {route.name}
            </LinkAnimated>
          ))}
        </ul>
      </SheetContent>
    </Sheet>
  );
};

export default BurgerMenu;
